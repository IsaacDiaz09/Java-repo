package Reto2.src;

class BaseDatosProductos {
	/**
	 * Aqui se produce el informe a mostrar en pantalla, prod precio menor, mayor,
	 * promedio y valor total.
	 */
	public static void generarInforme() {

		for (Producto p : Reto2.listaProductos.values()) {
			Reto2.Globals.ids.add(p.getId());
			Reto2.Globals.nombres.add(p.getName());
			Reto2.Globals.precios.add(p.getPrice());
			Reto2.Globals.inventario.add(p.getStock());
		}
		System.out.print(Reto2.mayor() + " " + Reto2.menor() + " " + Reto2.promedio() + " " + Reto2.valTotal());
	}

	/**
	 * 
	 * @param id
	 * @return boolean, verifica si un producto se escuentra ya en el HashMap
	 */
	protected static boolean verificarExistencia(int id) {
		return (Reto2.listaProductos.containsKey(id));
	}
}
