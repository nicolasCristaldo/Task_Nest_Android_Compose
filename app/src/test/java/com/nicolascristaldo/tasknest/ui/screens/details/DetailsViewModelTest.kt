package com.nicolascristaldo.tasknest.ui.screens.details

import com.nicolascristaldo.tasknest.domain.model.Category
import com.nicolascristaldo.tasknest.domain.model.Status
import com.nicolascristaldo.tasknest.domain.model.Task
import com.nicolascristaldo.tasknest.domain.usecase.DeleteTaskUseCase
import com.nicolascristaldo.tasknest.domain.usecase.GetTaskByIdUseCase
import com.nicolascristaldo.tasknest.domain.usecase.UpdateTaskUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {
    private val getTaskByIdUseCase: GetTaskByIdUseCase = mockk()
    private val deleteTaskUseCase: DeleteTaskUseCase = mockk()
    private val updateTaskUseCase: UpdateTaskUseCase = mockk()
    private lateinit var detailsViewModel: DetailsViewModel
    private val testDispatcher = UnconfinedTestDispatcher()
    private val sampleTask = Task(
        id = 1,
        name = "Task 1",
        category = Category.PERSONAL,
        status = Status.PENDING
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        detailsViewModel = DetailsViewModel(getTaskByIdUseCase, deleteTaskUseCase, updateTaskUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getTask retrieves task by id`() = runTest {
        //Arrange
        coEvery { getTaskByIdUseCase(sampleTask.id) } returns flowOf(sampleTask)

        //Act
        detailsViewModel.getTask(sampleTask.id)

        //Assert
        val result = detailsViewModel.task.value
        assertEquals(sampleTask, result)
    }

    @Test
    fun `getTask handles null task from use case`() = runTest {
        //Arrange
        coEvery { getTaskByIdUseCase(sampleTask.id) } returns flowOf(null)

        //Act
        detailsViewModel.getTask(sampleTask.id)

        //Assert
        val result = detailsViewModel.task.value
        assertEquals(null, result)
    }

    @Test
    fun `deleteTask calls deleteTaskUseCase with current task`() = runTest {
        //Arrange
        coEvery { getTaskByIdUseCase(sampleTask.id) } returns flowOf(sampleTask)
        coEvery { deleteTaskUseCase(sampleTask) } returns Unit

        //Act
        detailsViewModel.getTask(sampleTask.id)
        detailsViewModel.deleteTask()

        //Assert
        coVerify(exactly = 1) { deleteTaskUseCase(sampleTask) }
    }

    @Test
    fun `deleteTask does nothing if task is null`() = runTest {
        //Arrange
        coEvery { getTaskByIdUseCase(sampleTask.id) } returns flowOf(null)

        //Act
        detailsViewModel.getTask(sampleTask.id)
        detailsViewModel.deleteTask()

        //Assert
        coVerify(exactly = 0) { deleteTaskUseCase(any()) }
    }

    @Test
    fun `changeStatus updates task status from PENDING to IN_PROGRESS`() = runTest {
        //Arrange
        val updatedTask = sampleTask.copy(status = Status.IN_PROGRESS)
        coEvery { getTaskByIdUseCase(sampleTask.id) } returns flowOf(sampleTask)
        coEvery { updateTaskUseCase(updatedTask) } returns Unit

        //Act
        detailsViewModel.getTask(sampleTask.id)
        detailsViewModel.changeStatus()

        //Assert
        coVerify(exactly = 1) { updateTaskUseCase(updatedTask) }
    }

    @Test
    fun `changeStatus updates task status from IN_PROGRESS to COMPLETED`() = runTest {
        //Arrange
        val updatedTask = sampleTask.copy(status = Status.COMPLETED)
        coEvery { getTaskByIdUseCase(sampleTask.id) } returns flowOf(sampleTask.copy(status = Status.IN_PROGRESS))
        coEvery { updateTaskUseCase(updatedTask) } returns Unit

        //Act
        detailsViewModel.getTask(sampleTask.id)
        detailsViewModel.changeStatus()

        //Assert
        coVerify(exactly = 1) { updateTaskUseCase(updatedTask) }
    }

    @Test
    fun `changeStatus updates task status from COMPLETED to PENDING`() = runTest {
        //Arrange
        val updatedTask = sampleTask.copy(status = Status.PENDING)
        coEvery { getTaskByIdUseCase(sampleTask.id) } returns flowOf(sampleTask.copy(status = Status.COMPLETED))
        coEvery { updateTaskUseCase(updatedTask) } returns Unit

        //Act
        detailsViewModel.getTask(sampleTask.id)
        detailsViewModel.changeStatus()

        //Assert
        coVerify(exactly = 1) { updateTaskUseCase(updatedTask) }
    }

    @Test
    fun `changeStatus does nothing if task is null`() = runTest {
        //Arrange
        coEvery { getTaskByIdUseCase(sampleTask.id) } returns flowOf(null)

        //Act
        detailsViewModel.getTask(sampleTask.id)
        detailsViewModel.changeStatus()

        //Assert
        coVerify(exactly = 0) { updateTaskUseCase(any()) }
    }
}