package com.picpay.desafio.android

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.RecyclerViewMatchers.checkRecyclerViewItem
import com.picpay.desafio.android.presentation.ui.MainActivity
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test


class MainActivityTest {

    private val server = MockWebServer()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        executeCommands(listOf(WIFI_ENABLE, DATA_ENABLE))
    }

    @After
    fun finish() {
        executeCommands(listOf(WIFI_ENABLE, DATA_ENABLE))
    }

    @Test
    fun shouldDisplayTitle() {
        launchActivity<MainActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun whenSuccessShouldDisplayListItem() {
        server.dispatcher = getMockServerInstance()

        server.start(serverPort)

        launchActivity<MainActivity>().apply {

            onView(withId(R.id.user_list_progress_bar))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))

            onView(withId(R.id.recyclerView))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

            checkRecyclerViewItem(R.id.recyclerView, 0, withText("Eduardo Santos"))
            checkRecyclerViewItem(R.id.recyclerView, 0, withText("@eduardo.santos"))

        }

        server.close()
    }

    @Test
    fun whenErrorShouldUseCacheToDisplayListItem() {
        server.dispatcher = getMockServerInstance()

        server.start(serverPort)

        val scenario = launchActivity<MainActivity>()

        scenario.recreate()

        scenario.apply {

            executeCommands(listOf(WIFI_DISABLE, DATA_DISABLE))

            onView(withId(R.id.user_list_progress_bar))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))

            onView(withId(R.id.recyclerView))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

            checkRecyclerViewItem(R.id.recyclerView, 0, withText("Eduardo Santos"))
            checkRecyclerViewItem(R.id.recyclerView, 0, withText("@eduardo.santos"))


        }
        server.close()
    }


    private fun getMockServerInstance() = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/users" -> successResponse
                else -> errorResponse
            }
        }
    }

    companion object {
        private const val serverPort = 8080

        private const val WIFI_DISABLE = "svc wifi disable"
        private const val DATA_DISABLE = "svc data disable"
        private const val WIFI_ENABLE = "svc wifi enable"
        private const val DATA_ENABLE = "svc data enable"

        private val successResponse by lazy {
            val body =
                "[{\"id\":1001,\"name\":\"Eduardo Santos\",\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\"username\":\"@eduardo.santos\"}]"

            MockResponse()
                .setResponseCode(200)
                .setBody(body)
        }

        private val errorResponse by lazy { MockResponse().setResponseCode(404) }
    }
}