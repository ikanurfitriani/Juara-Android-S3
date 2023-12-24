// Nama package dari datasource yang dibuat dalam aplikasi
package com.ikanurfitriani.lunchtray.datasource

// Import library, kelas atau file yang dibutuhkan
import com.ikanurfitriani.lunchtray.model.MenuItem

// Membuat objek HeroesRepository
object DataSource {

    // Untuk membuat daftar menu
    val entreeMenuItems = listOf(
        MenuItem.EntreeItem(
            // Untuk menampilkan nama item
            name = "Cauliflower",
            // Untum menampilkan deskripsi item
            description = "Whole cauliflower, brined, roasted, and deep fried",
            // Untum menampilkan deskripsi item
            price = 7.00,
        ),
        MenuItem.EntreeItem(
            // Untuk menampilkan nama item
            name = "Three Bean Chili",
            // Untum menampilkan deskripsi item
            description = "Black beans, red beans, kidney beans, slow cooked, topped with onion",
            // Untum menampilkan deskripsi item
            price = 4.00,
        ),
        MenuItem.EntreeItem(
            // Untuk menampilkan nama item
            name = "Mushroom Pasta",
            // Untum menampilkan deskripsi item
            description = "Penne pasta, mushrooms, basil, with plum tomatoes cooked in garlic " +
                    "and olive oil",
            // Untum menampilkan deskripsi item
            price = 5.50,
        ),
        MenuItem.EntreeItem(
            // Untuk menampilkan nama item
            name = "Spicy Black Bean Skillet",
            // Untum menampilkan deskripsi item
            description = "Seasonal vegetables, black beans, house spice blend, served with " +
                    "avocado and quick pickled onions",
            // Untum menampilkan deskripsi item
            price = 5.50,
        )
    )

    // Untuk membuat daftar menu
    val sideDishMenuItems = listOf(
        MenuItem.SideDishItem(
            // Untuk menampilkan nama item
            name = "Summer Salad",
            // Untum menampilkan deskripsi item
            description = "Heirloom tomatoes, butter lettuce, peaches, avocado, balsamic dressing",
            // Untum menampilkan deskripsi item
            price = 2.50,
        ),
        MenuItem.SideDishItem(
            // Untuk menampilkan nama item
            name = "Butternut Squash Soup",
            // Untum menampilkan deskripsi item
            description = "Roasted butternut squash, roasted peppers, chili oil",
            // Untum menampilkan deskripsi item
            price = 3.00,
        ),
        MenuItem.SideDishItem(
            // Untuk menampilkan nama item
            name = "Spicy Potatoes",
            // Untum menampilkan deskripsi item
            description = "Marble potatoes, roasted, and fried in house spice blend",
            // Untum menampilkan deskripsi item
            price = 2.00,
        ),
        MenuItem.SideDishItem(
            // Untuk menampilkan nama item
            name = "Coconut Rice",
            // Untum menampilkan deskripsi item
            description = "Rice, coconut milk, lime, and sugar",
            // Untum menampilkan deskripsi item
            price = 1.50,
        )
    )

    // Untuk membuat daftar menu
    val accompanimentMenuItems = listOf(
        MenuItem.AccompanimentItem(
            // Untuk menampilkan nama item
            name = "Lunch Roll",
            // Untum menampilkan deskripsi item
            description = "Fresh baked roll made in house",
            // Untum menampilkan deskripsi item
            price = 0.50,
        ),
        MenuItem.AccompanimentItem(
            // Untuk menampilkan nama item
            name = "Mixed Berries",
            // Untum menampilkan deskripsi item
            description = "Strawberries, blueberries, raspberries, and huckleberries",
            // Untum menampilkan deskripsi item
            price = 1.00,
        ),
        MenuItem.AccompanimentItem(
            // Untuk menampilkan nama item
            name = "Pickled Veggies",
            // Untum menampilkan deskripsi item
            description = "Pickled cucumbers and carrots, made in house",
            // Untum menampilkan deskripsi item
            price = 0.50,
        )
    )
}