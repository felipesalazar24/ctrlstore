package com.example.ctrlstore.data.repository

import com.example.ctrlstore.domain.model.Product

open class ProductRepository {

    private val baseImageUrl = "https://raw.githubusercontent.com/felipesalazar24/ctrlstore-images/main"

    fun getAllProducts(): List<Product> {
        return listOf(
            // MOUSES
            Product(
                id = 1,
                nombre = "Logitech G502",
                precio = 83000.0,
                imagen = "$baseImageUrl/products/Mouse/Logitech%20G502(1).jpg",
                descripcion = "El Logitech G502 LIGHTSPEED es un mouse inalámbrico diseñado para gamers que buscan un alto rendimiento, precisión y libertad de movimiento sin cables.",
                miniaturas = listOf(
                    "$baseImageUrl/products/Mouse/Logitech%20G502(2).jpg",
                    "$baseImageUrl/products/Mouse/Logitech%20G502(3).jpg",
                    "$baseImageUrl/products/Mouse/Logitech%20G502(4).jpg"
                ),
                atributo = "Mouse"
            ),
            Product(
                id = 2,
                nombre = "Logitech G305 LightSpeed Wireless",
                precio = 35000.0,
                imagen = "$baseImageUrl/products/Mouse/Logitech%20G305%20LightSpeed%20Wireless(1).jpg",
                descripcion = "El Logitech G305 LightSpeed es un mouse inalámbrico diseñado para gamers y usuarios que buscan un rendimiento profesional con tecnología avanzada.",
                miniaturas = listOf(
                    "$baseImageUrl/products/Mouse/Logitech%20G305%20LightSpeed%20Wireless(2).jpg",
                    "$baseImageUrl/products/Mouse/Logitech%20G305%20LightSpeed%20Wireless(3).jpg",
                    "$baseImageUrl/products/Mouse/Logitech%20G305%20LightSpeed%20Wireless(4).jpg"
                ),
                atributo = "Mouse"
            ),
            Product(
                id = 3,
                nombre = "Logitech G203 Lightsync Blue",
                precio = 20000.0,
                imagen = "$baseImageUrl/products/Mouse/Logitech%20G203%20Lightsync%20Blue(1).jpg",
                descripcion = "El Logitech G203 Lightsync Black es un mouse gamer alámbrico diseñado para ofrecer precisión, personalización y rendimiento en juegos.",
                miniaturas = listOf(
                    "$baseImageUrl/products/Mouse/Logitech%20G203%20Lightsync%20Blue(2).jpg",
                    "$baseImageUrl/products/Mouse/Logitech%20G203%20Lightsync%20Blue(3).jpg",
                    "$baseImageUrl/products/Mouse/Logitech%20G203%20Lightsync%20Blue(4).jpg"
                ),
                atributo = "Mouse"
            ),

            // TECLADOS
            Product(
                id = 4,
                nombre = "Redragon Kumara K552 Rainbow",
                precio = 26000.0,
                imagen = "$baseImageUrl/products/Teclado/Redragon%20Kumara%20K552%20Rainbow(1).jpg",
                descripcion = "El Redragon Kumara K552 Rainbow es un teclado mecánico, diseñado especialmente para gamers y usuarios que buscan un periférico resistente.",
                miniaturas = listOf(
                    "$baseImageUrl/products/Teclado/Redragon%20Kumara%20K552%20Rainbow(2).jpg",
                    "$baseImageUrl/products/Teclado/Redragon%20Kumara%20K552%20Rainbow(3).jpg",
                    "$baseImageUrl/products/Teclado/Redragon%20Kumara%20K552%20Rainbow(4).jpg"
                ),
                atributo = "Teclado"
            ),
            Product(
                id = 5,
                nombre = "Logitech G PRO X TKL",
                precio = 182000.0,
                imagen = "$baseImageUrl/products/Teclado/Logitech%20G%20PRO%20X%20TKL(1).jpg",
                descripcion = "El Logitech PRO X TKL Lightspeed es un teclado mecánico diseñado para jugadores profesionales y entusiastas del gaming.",
                miniaturas = listOf(
                    "$baseImageUrl/products/Teclado/Logitech%20G%20PRO%20X%20TKL(2).jpg",
                    "$baseImageUrl/products/Teclado/Logitech%20G%20PRO%20X%20TKL(3).jpg",
                    "$baseImageUrl/products/Teclado/Logitech%20G%20PRO%20X%20TKL(4).jpg"
                ),
                atributo = "Teclado"
            ),
            Product(
                id = 6,
                nombre = "Razer BlackWidow V4 75% - Black",
                precio = 165000.0,
                imagen = "$baseImageUrl/products/Teclado/Razer%20BlackWidow%20V4%2075%20-%20Black(1).jpg",
                descripcion = "El Razer BlackWidow V4 75% es un teclado mecánico compacto diseñado para usuarios y gamers que buscan un equilibrio.",
                miniaturas = listOf(
                    "$baseImageUrl/products/Teclado/Razer%20BlackWidow%20V4%2075%20-%20Black(2).jpg",
                    "$baseImageUrl/products/Teclado/Razer%20BlackWidow%20V4%2075%20-%20Black(3).jpg",
                    "$baseImageUrl/products/Teclado/Razer%20BlackWidow%20V4%2075%20-%20Black(4).jpg"
                ),
                atributo = "Teclado"
            ),

            // AUDÍFONOS
            Product(
                id = 7,
                nombre = "Logitech G435 - Black/Yellow",
                precio = 58000.0,
                imagen = "$baseImageUrl/products/Audifono/Logitech%20G435%20-%20Black/Yellow(1).jpg",
                descripcion = "Los Logitech G435 son audífonos inalámbricos diseñados especialmente para gaming, que combinan la tecnología LIGHTSPEED y Bluetooth.",
                miniaturas = listOf(
                    "$baseImageUrl/products/Audifono/Logitech%20G435%20-%20Black/Yellow(2).jpg",
                    "$baseImageUrl/products/Audifono/Logitech%20G435%20-%20Black/Yellow(3).jpg"
                ),
                atributo = "Audifono"
            ),
            Product(
                id = 8,
                nombre = "Razer BlackShark V2 X",
                precio = 37000.0,
                imagen = "$baseImageUrl/products/Audifono/Razer%20BlackShark%20V2%20X(1).jpg",
                descripcion = "Los Razer BlackShark V2 X son audífonos diseñados especialmente para gamers y entusiastas de los esports.",
                miniaturas = listOf(
                    "$baseImageUrl/products/Audifono/Razer%20BlackShark%20V2%20X(2).jpg",
                    "$baseImageUrl/products/Audifono/Razer%20BlackShark%20V2%20X(3).jpg",
                    "$baseImageUrl/products/Audifono/Razer%20BlackShark%20V2%20X(4).jpg"
                ),
                atributo = "Audifono"
            ),
            Product(
                id = 9,
                nombre = "Logitech G335 - Black",
                precio = 43000.0,
                imagen = "$baseImageUrl/products/Audifono/Logitech%20G335%20-%20Black(1).jpg",
                descripcion = "Los Logitech G335 son audífonos gamer diseñados para ofrecer una experiencia de sonido clara y envolvente.",
                miniaturas = listOf(
                    "$baseImageUrl/products/Audifono/Logitech%20G335%20-%20Black(2).jpg",
                    "$baseImageUrl/products/Audifono/Logitech%20G335%20-%20Black(3).jpg"
                ),
                atributo = "Audifono"
            ),

            // MONITORES
            Product(
                id = 10,
                nombre = "LG UltraGear 24GS60F-B",
                precio = 119000.0,
                imagen = "$baseImageUrl/products/Monitor/LG%20UltraGear%2024GS60F-B(1).jpg",
                descripcion = "El LG UltraGear 24GS60F-B es un monitor diseñado para gamers que buscan un rendimiento superior y una experiencia visual inmersiva.",
                miniaturas = listOf(
                    "$baseImageUrl/products/Monitor/LG%20UltraGear%2024GS60F-B(2).jpg",
                    "$baseImageUrl/products/Monitor/LG%20UltraGear%2024GS60F-B(3).jpg",
                    "$baseImageUrl/products/Monitor/LG%20UltraGear%2024GS60F-B(4).jpg"
                ),
                atributo = "Monitor"
            ),
            Product(
                id = 11,
                nombre = "Xiaomi A27Qi",
                precio = 124000.0,
                imagen = "$baseImageUrl/products/Monitor/Xiaomi%20A27Qi(1).jpg",
                descripcion = "El Monitor Xiaomi A27Qi es una pantalla de 27 pulgadas que ofrece una experiencia visual de alta calidad.",
                miniaturas = listOf(
                    "$baseImageUrl/products/Monitor/Xiaomi%20A27Qi(2).jpg",
                    "$baseImageUrl/products/Monitor/Xiaomi%20A27Qi(3).jpg",
                    "$baseImageUrl/products/Monitor/Xiaomi%20A27Qi(4).jpg"
                ),
                atributo = "Monitor"
            ),
            Product(
                id = 12,
                nombre = "Xiaomi G34WQi",
                precio = 240000.0,
                imagen = "$baseImageUrl/products/Monitor/Xiaomi%20G34WQi(1).jpg",
                descripcion = "El Monitor Gamer Xiaomi G34WQi es una pantalla curva ultrapanorámica de 34 pulgadas diseñada para ofrecer una experiencia visual inmersiva.",
                miniaturas = listOf(
                    "$baseImageUrl/products/Monitor/Xiaomi%20G34WQi(2).jpg",
                    "$baseImageUrl/products/Monitor/Xiaomi%20G34WQi(3).jpg",
                    "$baseImageUrl/products/Monitor/Xiaomi%20G34WQi(4).jpg"
                ),
                atributo = "Monitor"
            )
        )
    }

    fun getProductsByCategory(category: String): List<Product> {
        return getAllProducts().filter { it.atributo.equals(category, ignoreCase = true) }
    }
}
