package com.example.alex.statemachineexample.statemachine

import com.example.alex.statemachineexample.Sandwich
import com.example.alex.statemachineexample.statemachine.ViewState.AddSandwichViewState

class AddSandwich(private val existingSandwiches: List<Sandwich>) : SandwichState {

  override val viewState: ViewState
    get() = AddSandwichViewState()

  override fun consumeAction(action: Action): StateChange {
    return when (action) {
      is Action.SandwichTypeSelected -> StateChange(
        DescribeSandwich(
          existingSandwiches,
          action.type
        )
      )
      is Action.PredefinedSandwichSelected -> StateChange(SandwichList(existingSandwiches + action.sandwich))
      else -> throw IllegalStateException("Invalid action $action passed to state $this")
    }
  }
}