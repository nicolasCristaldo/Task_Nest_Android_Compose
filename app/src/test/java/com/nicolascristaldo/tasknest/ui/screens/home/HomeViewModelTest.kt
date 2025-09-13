package com.nicolascristaldo.tasknest.ui.screens.home

import com.nicolascristaldo.tasknest.domain.model.Category
import com.nicolascristaldo.tasknest.domain.model.Status
import com.nicolascristaldo.tasknest.domain.model.Task
import com.nicolascristaldo.tasknest.domain.usecase.GetTasksUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private val getTasksUseCase: GetTasksUseCase = mockk()
    private lateinit var homeViewModel: HomeViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init loads tasks and updates uiState`() {
        //Arrange
        val tasks = listOf(
            Task(id = 1, name = "Task 1", category = Category.PERSONAL, status = Status.PENDING),
            Task(id = 2, name = "Task 2", category = Category.WORK, status = Status.IN_PROGRESS)
        )
        coEvery { getTasksUseCase(null) } returns flowOf(tasks)

        //Act
        homeViewModel = HomeViewModel(getTasksUseCase)

        //Assert
        val uiState = homeViewModel.uiState.value
        assertEquals(tasks, uiState.tasks)
        assertFalse(uiState.isLoading)
        assertNull(uiState.error)
        assertNull(uiState.nameFilter)
    }

    @Test
    fun `init handles error and updates uiState`() = runTest {
        //Arrange
        val errorMessage = "Error"
        coEvery { getTasksUseCase(null) } returns flow { throw Exception(errorMessage) }

        //Act
        homeViewModel = HomeViewModel(getTasksUseCase)

        //Assert
        val uiState = homeViewModel.uiState.value
        assertEquals(errorMessage, uiState.error)
        assertTrue(uiState.tasks.isEmpty())
        assertFalse(uiState.isLoading)
        assertNull(uiState.nameFilter)
    }

    @Test
    fun `setNameFilter updates nameFilter and loads tasks`() = runTest {
        //Arrange
        val filter = "Test"
        val tasks = listOf(Task(id = 1, name = "Test Task", category = Category.PERSONAL, status = Status.PENDING))
        coEvery { getTasksUseCase(null) } returns flowOf(emptyList())
        coEvery { getTasksUseCase(filter) } returns flowOf(tasks)

        //Act
        homeViewModel = HomeViewModel(getTasksUseCase)
        homeViewModel.setNameFilter(filter)

        //Assert
        val uiState = homeViewModel.uiState.value
        assertEquals(filter, uiState.nameFilter)
        assertEquals(tasks, uiState.tasks)
        assertFalse(uiState.isLoading)
        assertNull(uiState.error)
    }

    @Test
    fun `setNameFilter with null clears filter and reload tasks`() = runTest {
        //Arrange
        val tasks = listOf(Task(id = 1, name = "Task 1", category = Category.PERSONAL, status = Status.PENDING))
        coEvery { getTasksUseCase(null) } returns flowOf(tasks)

        // Act
        homeViewModel = HomeViewModel(getTasksUseCase)
        homeViewModel.setNameFilter(null)

        //Assert
        val uiState = homeViewModel.uiState.value
        assertNull(uiState.nameFilter)
        assertEquals(tasks, uiState.tasks)
        assertFalse(uiState.isLoading)
        assertNull(uiState.error)
    }

    @Test
    fun `setNameFilter handles error and updates uiState`() = runTest {
        //Arrange
        val filter = "Test"
        val errorMessage = "Error"
        coEvery { getTasksUseCase(null) } returns flowOf(emptyList())
        coEvery { getTasksUseCase(filter) } returns flow { throw Exception(errorMessage) }

        //Act
        homeViewModel = HomeViewModel(getTasksUseCase)
        homeViewModel.setNameFilter(filter)

        //Assert
        val uiState = homeViewModel.uiState.value
        assertEquals(errorMessage, uiState.error)
        assertEquals(filter, uiState.nameFilter)
        assertTrue(uiState.tasks.isEmpty())
        assertFalse(uiState.isLoading)
    }
}