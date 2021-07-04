package tw.com.hmbus

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import tw.com.hmbus.domain.SearchRouteCase
import tw.com.hmbus.ui.searchRoute.SearchRouteViewModel

class SearchRouteViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    private val searchRouteCase = mockk<SearchRouteCase>()
    private lateinit var viewModel: SearchRouteViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        viewModel = SearchRouteViewModel(mockk(), searchRouteCase)

        every { searchRouteCase.executeAndGet(any()) } returns flowOf(listOf())
    }

    @After()
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `search route success and result should not be null`() {
        viewModel.searchBusRoute("299")
        assertNotNull(viewModel.busRouteResult.value)
    }

    @Test
    fun `search empty route and should not be searched`() {
        viewModel.searchBusRoute("")
        verify(exactly = 0) { searchRouteCase.executeAndGet(any()) }
    }

    @Test
    fun `search duplicated route and should be searched once`() {
        viewModel.searchBusRoute("299")
        viewModel.searchBusRoute("299")
        verify(exactly = 1) { searchRouteCase.executeAndGet(any()) }
    }
}
