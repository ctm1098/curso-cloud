package es.um.atica.umufly;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.domain.JavaParameter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import jakarta.validation.Valid;

@AnalyzeClasses(packages ="es.um.atica.umufly")
public class UmuFlyArquitecturaTests {

	private static final DescribedPredicate<JavaClass> IMPLEMENTA_ALGUNA_INTERFAZ = new DescribedPredicate<JavaClass>( "Las implementaciones deben terminar en Impl" ) {

		@Override
		public boolean test( JavaClass javaClass ) {
			return !javaClass.getInterfaces().isEmpty();
		}
	};


	private static final ArchCondition<JavaMethod> METODO_REST_VALIDA_PARAMETROS =
			new ArchCondition<>("Rest debe tener @Valid o @Validated en parámetros @RequestBody") {

		@Override
		public void check(JavaMethod metodo, ConditionEvents events) {
			boolean validaParametro = false;
			for (JavaParameter parametro : metodo.getParameters()) {
				//Compruebo si el metodo tiene RequestBody (JSON)
				if (parametro.isAnnotatedWith(RequestBody.class)) {
					validaParametro = parametro.isAnnotatedWith(Valid.class)
							|| parametro.isAnnotatedWith(Validated.class)
							|| metodo.getOwner().isAnnotatedWith(Validated.class);
					if (!validaParametro) {
						String message = String.format("El método %s tiene un @RequestBody sin validación",
								metodo.getFullName());
						//Aniado un evento de violacion
						events.add(SimpleConditionEvent.violated(metodo, message));
					}

				}
			}
		}};

		private static final ArchCondition<JavaClass> CLASE_NO_TIENE_MAS_20_METODOS_PUBLICOS =
				new ArchCondition<>("Una clase no puede tener más de 20 métodos públicos") {

			@Override
			public void check(JavaClass clase, ConditionEvents events) {
				if ( clase.getMethods().stream().filter( m -> m.getModifiers().contains( JavaModifier.PUBLIC ) ).toList().size() > 20 ) {
					String message = String.format("La clase %s tiene más de 20 métodos públicos",
							clase.getFullName());
					//Aniado un evento de violacion
					events.add(SimpleConditionEvent.violated(clase, message));
				}
			}

		};

		// solicitudes

		@ArchTest
		static final ArchRule ninguna_interfaz_acaba_en_impl = noClasses().that().areInterfaces().should().haveNameMatching( "^.*Impl$" ).because( "Ninguna clase debe terminar en con \"Impl\"" );

		@ArchTest
		static final ArchRule codigo_respeta_arquitectura_hexagonal = layeredArchitecture()// Define una estructura por capas
				.consideringAllDependencies() // Cojo todas las dependencias del proyecto
		.layer( "Domain" ).definedBy( "..domain.." ) // Defino la capa de dominio por todas las clases dentro de ..domain..
		.layer( "Application" ).definedBy( "..application.." ) // Defino la capa de aplicacion por todas las clases dentro de ..application..
		.layer( "Adapters" ).definedBy( "..adaptors.." )// Defino la capa de adaptadores por todas las clases dentro de ..adaptors..
		.whereLayer( "Domain" ).mayOnlyBeAccessedByLayers( "Application", "Adapters" ) // Indico que "imports" de la
		// capa de dominio pueden estar en Application y Adapters
		.whereLayer( "Application" ).mayOnlyBeAccessedByLayers( "Adapters" )// Indico que "imports" de la capa de
		// adaptadores pueden estar en Application (pero no en dominio)
		.whereLayer( "Adapters" ).mayNotBeAccessedByAnyLayer(); // Ninguna capa debe tener import de adapters

		@ArchTest
		static final ArchRule api_rest_debe_validar_datos_entrada = methods().that().areDeclaredInClassesThat().areAnnotatedWith( RestController.class ).and().arePublic().should( METODO_REST_VALIDA_PARAMETROS );

		@ArchTest
		static final ArchRule dto_debe_terminar_en_dto = classes().that().resideInAPackage( "..dto" ).and().areNotEnums().should().haveSimpleNameEndingWith( "DTO" ).because( "Todos los DTOs deben terminar en \"DTO\"" );

		@ArchTest
		static final ArchRule implementacion_debe_terminar_en_impl = classes().that().areNotInterfaces().and().areNotEnums().and( IMPLEMENTA_ALGUNA_INTERFAZ ).and().haveNameNotMatching( "^.*(Repository|Handler|Adapter|Assembler.*)$" ).should()
		.haveSimpleNameEndingWith( "Impl" )
		.because( "Todas las implementaciones deben terminar en \"Impl\"" )
		.allowEmptyShould( true );

		@ArchTest
		static final ArchRule rest_controller_en_capa_adaptadores = classes().that().areAnnotatedWith( RestController.class ).should().resideInAPackage( "..adaptors.." ).because( "Todos los RestController deberían estar en la capa Adapters" );

		@ArchTest
		static final ArchRule ninguna_clase_tiene_mas_de_20_metodos_publicos = classes().that().resideOutsideOfPackage( "..entity" ).should( CLASE_NO_TIENE_MAS_20_METODOS_PUBLICOS );
}

