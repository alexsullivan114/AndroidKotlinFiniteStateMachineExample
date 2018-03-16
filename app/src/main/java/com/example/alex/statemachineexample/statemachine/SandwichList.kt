package com.example.alex.statemachineexample.statemachine

import com.example.alex.statemachineexample.Sandwich
import com.example.alex.statemachineexample.statemachine.ViewState.SandwichListViewState

class SandwichList(private val sandwiches: List<Sandwich>): SandwichState {

  override val viewState: ViewState
    get() = SandwichListViewState(sandwiches)

  override fun consumeAction(action: Action): StateChange {
    return when(action) {
      is Action.AddSandwichClicked -> StateChange(AddSandwich(sandwiches))
      else -> throw IllegalStateException("Invalid action $action passed to state $this")
    }
  }
}