package com.nicolascristaldo.tasknest.ui.screens.form

import android.content.Context
import com.nicolascristaldo.tasknest.domain.model.Category
import com.nicolascristaldo.tasknest.domain.model.Status
import com.nicolascristaldo.tasknest.domain.model.Task
import com.nicolascristaldo.tasknest.domain.usecase.GetTaskByIdUseCase
import com.nicolascristaldo.tasknest.domain.usecase.InsertTaskUseCase
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
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class FormViewmodelTest {
    private val context: Context = mockk()
    private val getTaskByIdUseCase: GetTaskByIdUseCase = mockk()
    private val insertTaskUseCase: InsertTaskUseCase = mockk()
    private val updateTaskUseCase: UpdateTaskUseCase = mockk()
    private lateinit var formViewmodel: FormViewmodel
    private val testDispatcher = UnconfinedTestDispatcher()
    private val sampleTask = Task(
        id = 1,
        name = "Task 1",
        category = Category.PERSONAL,
        status = Status.PENDING
    )
    private val sampleTaskDetails = sampleTask.toTaskDetails()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        coEvery { context.getString(any<Int>(), any<String>()) } answers { "Error: ${it.invocation.args[1]}" }
        formViewmodel = FormViewmodel(context, getTaskByIdUseCase, insertTaskUseCase, updateTaskUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getTask retrieves task by id and updates uiState`() = runTest {
        //Arrange
        coEvery { getTaskByIdUseCase(sampleTask.id) } returns flowOf(sampleTask)

        //Act
        formViewmodel.getTask(sampleTask.id)

        //Assert
        val uiState = formViewmodel.uiState.value
        assertEquals(sampleTaskDetails, uiState.taskDetails)
        assertNull(uiState.error)
    }

    @Test
    fun `getTask does not update uiState if task is null`() = runTest {
        //Arrange
        coEvery { getTaskByIdUseCase(sampleTask.id) } returns flowOf(null)

        //Act
        formViewmodel.getTask(sampleTask.id)

        //Assert
        val uiState = formViewmodel.uiState.value
        assertEquals(TaskDetails(), uiState.taskDetails)
        assertNull(uiState.error)
    }

    @Test
    fun `updateUiState updates taskDetails in uiState`() {
        //Arrange
        val updatedTaskDetails = sampleTaskDetails.copy(name = "Updated Task")

        //Act
        formViewmodel.updateUiState(updatedTaskDetails)

        //Assert
        val uiState = formViewmodel.uiState.value
        assertEquals(updatedTaskDetails, uiState.taskDetails)
        assertNull(uiState.error)
    }

    @Test
    fun `saveTask calls insertTaskUseCase when task is valid`() = runTest {
        //Arrange
        coEvery { insertTaskUseCase(sampleTask) } returns Unit

        //Act
        formViewmodel.updateUiState(sampleTaskDetails)
        formViewmodel.saveTask()

        //Assert
        coVerify(exactly = 1) { insertTaskUseCase(sampleTask) }
        assertNull(formViewmodel.uiState.value.error)
    }

    @Test
    fun `saveTask does nothing if task is not valid`() = runTest {
        //Arrange
        val invalidTaskDetails = sampleTaskDetails.copy(name = "")
        coEvery { insertTaskUseCase(invalidTaskDetails.toTask()) } returns Unit

        //Act
        formViewmodel.updateUiState(invalidTaskDetails)
        formViewmodel.saveTask()

        //Assert
        coVerify(exactly = 0) { insertTaskUseCase(invalidTaskDetails.toTask()) }
        assertNull(formViewmodel.uiState.value.error)
    }

    @Test
    fun `saveTask updates uiState with error message if insertTaskUseCase fails`() = runTest {
        //Arrange
        val errorMessage = "Error saving task"
        coEvery { insertTaskUseCase(sampleTask) } throws Exception(errorMessage)

        //Act
        formViewmodel.updateUiState(sampleTaskDetails)
        formViewmodel.saveTask()

        //Assert
        val uiState = formViewmodel.uiState.value
        coVerify(exactly = 1) { insertTaskUseCase(sampleTaskDetails.toTask()) }
        assertNotNull(uiState.error)
    }

    @Test
    fun `updateTask calls updateTaskUseCase when task is valid`() = runTest {
        //Arrange
        coEvery { updateTaskUseCase(sampleTask) } returns Unit

        //Act
        formViewmodel.updateUiState(sampleTaskDetails)
        formViewmodel.updateTask()

        //Assert
        coVerify(exactly = 1) { updateTaskUseCase(sampleTask) }
        assertNull(formViewmodel.uiState.value.error)
    }

    @Test
    fun `updateTask does nothing if task is not valid`() = runTest {
        //Arrange
        val invalidTaskDetails = sampleTaskDetails.copy(name = "")
        coEvery { updateTaskUseCase(invalidTaskDetails.toTask()) } returns Unit

        //Act
        formViewmodel.updateUiState(invalidTaskDetails)
        formViewmodel.updateTask()

        //Assert
        coVerify(exactly = 0) { updateTaskUseCase(invalidTaskDetails.toTask()) }
        assertNull(formViewmodel.uiState.value.error)
    }

    @Test
    fun `updateTask updates uiState with error message if updateTaskUseCase fails`() = runTest {
        //Arrange
        val errorMessage = "Error saving task"
        coEvery { updateTaskUseCase(sampleTask) } throws Exception(errorMessage)

        //Act
        formViewmodel.updateUiState(sampleTaskDetails)
        formViewmodel.updateTask()

        //Assert
        val uiState = formViewmodel.uiState.value
        coVerify(exactly = 1) { updateTaskUseCase(sampleTaskDetails.toTask()) }
        assertNotNull(uiState.error)
    }
}