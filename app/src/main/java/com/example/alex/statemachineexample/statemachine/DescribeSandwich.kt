package com.example.alex.statemachineexample.statemachine

import com.example.alex.statemachineexample.Sandwich
import com.example.alex.statemachineexample.SandwichType
import com.example.alex.statemachineexample.statemachine.Command.SaveSandwich
import com.example.alex.statemachineexample.statemachine.ViewState.DescribeSandwichViewState

class DescribeSandwich(
  private val existingSandwiches: List<Sandwich>,
  private val newSandwichType: SandwichType
) : SandwichState {

  override val viewState: ViewState
    get() = DescribeSandwichViewState()

  override fun consumeAction(action: Action): StateChange {
    return when (action) {
      is Action.SubmitSandwichClicked -> {
        val newSandwich = Sandwich(action.sandwichName, newSandwichType)
        val newState = SandwichList(existingSandwiches + newSandwich)
        val command = SaveSandwich(newSandwich)
        StateChange(newState, command)
      }
      else -> throw IllegalStateException("Invalid action $action passed to state $this")
    }
  }
}