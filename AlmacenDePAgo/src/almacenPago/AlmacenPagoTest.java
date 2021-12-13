
/*...............................................................................
				PRUEBA CON JUNIT
...............................................................................*/

package almacenPago;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AlmacenPagoTest {
	AlmacenPago miPago1;
	AlmacenPago miPago2;
	AlmacenPago miPago3;

	@Before
	public void setUp() {
		miPago1 = new AlmacenPago(1000, 0.24);
		miPago2 = new AlmacenPago(1000, 0.71355714);
		miPago3 = new AlmacenPago(1000, 0.07);
	}
	
	@After 
	public void tearDown() {
		miPago1 = null;
		miPago2 = null;
		miPago3 = null;
	}
	
	double esperado = 0;
	double obtenido = 0;
	
	@Test
	public void funcionamientoConLosMetodosDelEnunciado() {
		esperado = 2364.21;
		obtenido = miPago1.calcularInversion(4);
		assertEquals(esperado, obtenido, 0.001); 			// METODO 1, EJEMPLO 1
		//--------------------------------------
		esperado = 14773.83;
		obtenido = miPago2.calcularInversion(5);
		assertEquals(esperado, obtenido, 0.001); 			// METODO 1, EJEMPLO 2
		//--------------------------------------
		esperado = 120;
		obtenido = miPago3.consultarCantidadDeMesesEstimados(2000);
		assertEquals(esperado, obtenido, 0); 				// METODO 2, EJEMPLO 1
		//--------------------------------------
		// Se hace un Set al Valor Original para Reutilizar miPago1
		miPago1.setMontoAinvertir(1000); 
		esperado = 48;
		obtenido = miPago1.consultarCantidadDeMesesEstimados(2587);
		assertEquals(esperado, obtenido, 0); 				// METODO 2, EJEMPLO 2
	}
	
	@Test (expected = Error.class)
	public void montoAInvertirNegativo () {
		miPago1.setMontoAinvertir(-1000);
	}
	
	@Test (expected = Error.class)
	public void interesAnualNegativo () {
		miPago2.setInteresAnual(-0.71355714);
	}
	
	@Test (expected = Error.class)
	public void cantidadMaximaDeAniosNegativa () {
		obtenido = miPago1.calcularInversion(-4);
	}
	
	@Test (expected = Error.class)
	public void saldoFinalDeseadoNegativo () {
		obtenido = miPago3.consultarCantidadDeMesesEstimados(-2000);
	}

}

