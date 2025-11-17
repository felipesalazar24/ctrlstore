package com.example.ctrlstore.data.repository

import com.example.ctrlstore.domain.model.Product

class ProductRepository {

    private val baseImageUrl = "https://raw.githubusercontent.com/felipesalazar24/ctrlstore-images/main"

    fun getAllProducts(): List<Product> {
        return listOf(
            // MOUSES
            Product(
                id = 1,
                nombre = "Logitech G502",
                precio = 83000,
                imagen = "$baseImageUrl/products/mouses/g502 (1).jpg",
                descripcion = "El Logitech G502 LIGHTSPEED es un mouse inalámbrico diseñado para gamers que buscan un alto rendimiento, precisión y libertad de movimiento sin cables.",
                miniaturas = listOf(
                    "$baseImageUrl/products/mouses/g502 (2).jpg",
                    "$baseImageUrl/products/mouses/g502 (3).jpg",
                    "$baseImageUrl/products/mouses/g502 (4).jpg"
                ),
                atributo = "Mouse"
            ),
            Product(
                id = 2,
                nombre = "Logitech G305 LightSpeed Wireless",
                precio = 35000,
                imagen = "$baseImageUrl/products/mouses/g305 (1).jpg",
                descripcion = "El Logitech G305 LightSpeed es un mouse inalámbrico diseñado para gamers y usuarios que buscan un rendimiento profesional con tecnología avanzada.",
                miniaturas = listOf(
                    "$baseImageUrl/products/mouses/g305 (2).jpg",
                    "$baseImageUrl/products/mouses/g305 (3).jpg",
                    "$baseImageUrl/products/mouses/g305 (4).jpg"
                ),
                atributo = "Mouse"
            ),
            Product(
                id = 3,
                nombre = "Logitech G203 Lightsync Blue",
                precio = 20000,
                imagen = "$baseImageUrl/products/mouses/g203 (1).jpg",
                descripcion = "El Logitech G203 Lightsync Black es un mouse gamer alámbrico diseñado para ofrecer precisión, personalización y rendimiento en juegos.",
                miniaturas = listOf(
                    "$baseImageUrl/products/mouses/g203 (2).jpg",
                    "$baseImageUrl/products/mouses/g203 (3).jpg",
                    "$baseImageUrl/products/mouses/g203 (4).jpg"
                ),
                atributo = "Mouse"
            ),

            // TECLADOS
            Product(
                id = 4,
                nombre = "Redragon Kumara K552 Rainbow",
                precio = 26000,
                imagen = "$baseImageUrl/products/teclados/k552 (1).jpg",
                descripcion = "El Redragon Kumara K552 Rainbow es un teclado mecánico, diseñado especialmente para gamers y usuarios que buscan un periférico resistente.",
                miniaturas = listOf(
                    "$baseImageUrl/products/teclados/k552 (2).jpg",
                    "$baseImageUrl/products/teclados/k552 (3).jpg",
                    "$baseImageUrl/products/teclados/k552 (4).jpg"
                ),
                atributo = "Teclado"
            ),
            Product(
                id = 5,
                nombre = "Logitech G PRO X TKL",
                precio = 182000,
                imagen = "$baseImageUrl/products/teclados/pro_x_tkl (1).jpg",
                descripcion = "El Logitech PRO X TKL Lightspeed es un teclado mecánico diseñado para jugadores profesionales y entusiastas del gaming.",
                miniaturas = listOf(
                    "$baseImageUrl/products/teclados/pro_x_tkl (2).jpg",
                    "$baseImageUrl/products/teclados/pro_x_tkl (3).jpg",
                    "$baseImageUrl/products/teclados/pro_x_tkl (4).jpg"
                ),
                atributo = "Teclado"
            ),
            Product(
                id = 6,
                nombre = "Razer BlackWidow V4 75% - Black",
                precio = 165000,
                imagen = "$baseImageUrl/products/teclados/blackwidow (1).jpg",
                descripcion = "El Razer BlackWidow V4 75% es un teclado mecánico compacto diseñado para usuarios y gamers que buscan un equilibrio.",
                miniaturas = listOf(
                    "$baseImageUrl/products/teclados/blackwidow (2).jpg",
                    "$baseImageUrl/products/teclados/blackwidow (3).jpg",
                    "$baseImageUrl/products/teclados/blackwidow (4).jpg"
                ),
                atributo = "Teclado"
            ),

            // AUDIFONOS (nota: dice "audifinos" en tu estructura)
            Product(
                id = 7,
                nombre = "Logitech G435 - Black/Yellow",
                precio = 58000,
                imagen = "$baseImageUrl/products/audifinos/g435 (1).jpg",
                descripcion = "Los Logitech G435 son audífonos inalámbricos diseñados especialmente para gaming, que combinan la tecnología LIGHTSPEED y Bluetooth.",
                miniaturas = listOf(
                    "$baseImageUrl/products/audifinos/g435 (2).jpg",
                    "$baseImageUrl/products/audifinos/g435 (3).jpg"
                ),
                atributo = "Audifono"
            ),
            Product(
                id = 8,
                nombre = "Razer BlackShark V2 X",
                precio = 37000,
                imagen = "$baseImageUrl/products/audifinos/blackshark (1).jpg",
                descripcion = "Los Razer BlackShark V2 X son audífonos diseñados especialmente para gamers y entusiastas de los esports.",
                miniaturas = listOf(
                    "$baseImageUrl/products/audifinos/blackshark (2).jpg",
                    "$baseImageUrl/products/audifinos/blackshark (3).jpg",
                    "$baseImageUrl/products/audifinos/blackshark (4).jpg"
                ),
                atributo = "Audifono"
            ),
            Product(
                id = 9,
                nombre = "Logitech G335 - Black",
                precio = 43000,
                imagen = "$baseImageUrl/products/audifinos/g335 (1).jpg",
                descripcion = "Los Logitech G335 son audífonos gamer diseñados para ofrecer una experiencia de sonido clara y envolvente.",
                miniaturas = listOf(
                    "$baseImageUrl/products/audifinos/g335 (2).jpg",
                    "$baseImageUrl/products/audifinos/g335 (3).jpg"
                ),
                atributo = "Audifono"
            ),

            // MONITORES
            Product(
                id = 10,
                nombre = "LG UltraGear 24GS60F-B",
                precio = 119000,
                imagen = "$baseImageUrl/products/monitores/ultragear (1).jpg",
                descripcion = "El LG UltraGear 24GS60F-B es un monitor diseñado para gamers que buscan un rendimiento superior y una experiencia visual inmersiva.",
                miniaturas = listOf(
                    "$baseImageUrl/products/monitores/ultragear (2).jpg",
                    "$baseImageUrl/products/monitores/ultragear (3).jpg",
                    "$baseImageUrl/products/monitores/ultragear (4).jpg"
                ),
                atributo = "Monitor"
            ),
            Product(
                id = 11,
                nombre = "Xiaomi A27Qi",
                precio = 124000,
                imagen = "$baseImageUrl/products/monitores/a27qi (1).jpg",
                descripcion = "El Monitor Xiaomi A27Qi es una pantalla de 27 pulgadas que ofrece una experiencia visual de alta calidad.",
                miniaturas = listOf(
                    "$baseImageUrl/products/monitores/a27qi (2).jpg",
                    "$baseImageUrl/products/monitores/a27qi (3).jpg",
                    "$baseImageUrl/products/monitores/a27qi (4).jpg"
                ),
                atributo = "Monitor"
            ),
            Product(
                id = 12,
                nombre = "Xiaomi G34WQi",
                precio = 240000,
                imagen = "$baseImageUrl/products/monitores/g34wqi (1).jpg",
                descripcion = "El Monitor Gamer Xiaomi G34WQi es una pantalla curva ultrapanorámica de 34 pulgadas diseñada para ofrecer una experiencia visual inmersiva.",
                miniaturas = listOf(
                    "$baseImageUrl/products/monitores/g34wqi (2).jpg",
                    "$baseImageUrl/products/monitores/g34wqi (3).jpg",
                    "$baseImageUrl/products/monitores/g34wqi (4).jpg"
                ),
                atributo = "Monitor"
            )
        )
    }

    fun getProductsByCategory(category: String): List<Product> {
        return getAllProducts().filter { it.atributo.equals(category, ignoreCase = true) }
    }
}