package com.flashpool.data.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.flashpool.data.local.FlashPoolDatabase
import com.flashpool.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ProductDaoTest {

    private lateinit var db: FlashPoolDatabase
    private lateinit var dao: ProductDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, FlashPoolDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.productDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun upsertAndObserveAll() = runTest {
        val products = listOf(
            ProductEntity("1", "Alpha", "url", 9.99, 10, "TECH", null),
            ProductEntity("2", "Beta", "url", 4.50, 5, "FMCG", null)
        )
        dao.upsertAll(products)

        val all = dao.observeAll().first()
        assertEquals(2, all.size)
        assertEquals("Alpha", all[0].title)
    }

    @Test
    fun observeByCategory_filtersCorrectly() = runTest {
        dao.upsertAll(
            listOf(
                ProductEntity("1", "Gadget", "url", 99.0, 3, "TECH", null),
                ProductEntity("2", "Snacks", "url", 2.0, 50, "FMCG", null)
            )
        )

        val techOnly = dao.observeByCategory("TECH").first()
        assertEquals(1, techOnly.size)
        assertEquals("Gadget", techOnly[0].title)
    }
}
