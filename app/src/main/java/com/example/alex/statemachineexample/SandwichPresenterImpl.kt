package com.example.alex.statemachineexample

import com.example.alex.statemachineexample.SandwichType.GRINDER
import com.example.alex.statemachineexample.SandwichType.WRAP
import com.example.alex.statemachineexample.statemachine.Action
import com.example.alex.statemachineexample.statemachine.Command
import com.example.alex.statemachineexample.statemachine.SandwichList
import com.example.alex.statemachineexample.statemachine.SandwichState
import com.example.alex.statemachineexample.statemachine.ViewState

class SandwichPresenterImpl(private val sandwichView: SandwichView): SandwichPresenter {
  private var currentState: SandwichState = SandwichList(emptyList())

  init {
    renderViewState(currentState.viewState, null)
  }

  override fun addSandwichClicked() {
    pushAction(Action.AddSandwichClicked())
  }

  override fun sandwichTypeClicked(type: SandwichType) {
    pushAction(Action.SandwichTypeSelected(type))
  }

  override fun predefinedSandwichClicked(sandwich: Sandwich) {
    pushAction(Action.PredefinedSandwichSelected(sandwich))
  }

  override fun submitSandwichClicked(sandwichName: String) {
    pushAction(Action.SubmitSandwichClicked(sandwichName))
  }

  fun pushAction(action: Action) {
    val oldViewState = currentState.viewState
    val stateChange = currentState.consumeAction(action)
    currentState = stateChange.newState
    stateChange.command?.let { handleCommand(it) }
    renderViewState(currentState.viewState, oldViewState)
  }

  private fun renderViewState(newState: ViewState, oldState: ViewState?) {

    when (newState) {
      is ViewState.DescribeSandwichViewState -> sandwichView.showSandwichInputFields()
      is ViewState.SandwichListViewState -> sandwichView.showSandwichList(newState.sandwiches)
      is ViewState.AddSandwichViewState -> { sandwichView.showAddSandwichView(predefinedSandwichList)
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
