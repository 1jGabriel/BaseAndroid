package com.example.baseproject.main

import com.example.baseproject.api.model.Movie
import com.example.baseproject.api.model.MovieResponse
import com.example.baseproject.repository.Repository
import com.example.baseproject.ui.main.MainActivityViewModel
import com.example.baseproject.util.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Unit test for [MainActivityViewModel].
 */
class MainActivityViewModelTest {

    @Mock
    private lateinit var mockRepository: Repository

    private val schedulerProvider = SchedulerProvider(Schedulers.trampoline(), Schedulers.trampoline())

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainActivityViewModel = MainActivityViewModel(mockRepository, schedulerProvider)
    }

    @Test
    fun showDataFromApi() {
        Mockito.`when`(mockRepository.getDataFromApi())
            .thenReturn(Single
                .just(MovieResponse(1,
                    arrayListOf(Movie(22, "asdasda", "sadasda", "asdasdads", 4.5)),
                    22, 22)))

        val testObserver = TestObserver<MovieResponse>()

        mainActivityViewModel.showDataFromApi()
                .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { movieResponse -> movieResponse.page == 1 }
        testObserver.assertValue { movieResponse -> movieResponse.totalPages == 22 }
        testObserver.assertValue { movieResponse -> movieResponse.totalResults == 22}
    }

    @Test
    fun showDataFromSearch(){
        Mockito.`when`(mockRepository
            .getMoviesFromSearch("teste"))
            .thenReturn(Single.just(MovieResponse(1, arrayListOf(Movie(22, "asdasda", "sadasda", "asdasdads", 4.5)), 22, 22)))

        val testObserver = TestObserver<MovieResponse>()

        mainActivityViewModel.showDataFromSearch("teste")
            .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { movieResponse -> movieResponse.page == 1 }

    }

    @Test
    fun showDataFromSearch2(){
        Mockito.`when`(mockRepository
            .getMoviesFromSearch2("teste"))
            .thenReturn(Observable.just(MovieResponse(1, arrayListOf(Movie(22, "asdasda", "sadasda", "asdasdads", 4.5)), 22, 22)))

        val testObserver = TestObserver<MovieResponse>()

        mainActivityViewModel.showDataFromSearch2("teste")
            .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { movieResponse -> movieResponse.page == 1 }

    }
}