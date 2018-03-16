package com.example.alex.statemachineexample

import com.example.alex.statemachineexample.SandwichType.GRINDER
import com.example.alex.statemachineexample.SandwichType.WRAP
import com.example.alex.statemachineexample.statemachine.Action
import com.example.alex.statemachineexample.statemachine.Command
import com.example.alex.statemachineexample.statemachine.SandwichList
import com.example.alex.statemachineexample.statemachine.SandwichState
import com.example.alex.statemachineexample.statemachine.ViewState

class SandwichPresenter(private val sandwichView: SandwichView) {
  private var currentState: SandwichState = SandwichList(emptyList())

  init {
    renderViewState(currentState.viewState, null)
  }

  fun acceptAction(action: Action) {
    val oldViewState = currentState.viewState
    val stateChange = currentState.consumeAction(action)
    currentState = stateChange.newState
    stateChange.command?.let { handleCommand(it) }
    renderViewState(currentState.viewState, oldViewState)
  }

  private fun renderViewState(newState: ViewState, oldState: ViewState?) {

    when (newState) {
      is ViewState.DescribeSandwichViewState -> sandwichView.showSandwichInputFields()
      is ViewState.SandwichListViewState -> sandwichView.showSandwichList(newState.sandwhiches)
      is ViewState.AddSandwichViewState -> {
        val predefinedSandwiches = listOf(
          Sandwich("Meatball", GRINDER),
          Sandwich("Italian", GRINDER),
          Sandwich("Caesar", WRAP)
        )
        sandwichView.showAddSandwichView(predefinedSandwiches)
      }
    }

    when (oldState) {
      is ViewState.AddSandwichViewState -> sandwichView.hideAddSandwichView()
      is ViewState.DescribeSandwichViewState -> sandwichView.hideSandwichInputFields()
      is ViewState.SandwichListViewState -> sandwichView.hideSandwichList()
    }
  }

  private fun handleCommand(command: Command) {
    when(command) {
      is Command.SaveSandwich -> print("Save to a database or push to the network!")
    }
  }

}

interface SandwichView {
  fun showSandwichList(sandwiches: List<Sandwich>)
  fun hideSandwichList()

  fun showAddSandwichView(predefinedSandwiches: List<Sandwich>)
  fun hideAddSandwichView()

  fun showSandwichInputFields()
  fun hideSandwichInputFields()
}