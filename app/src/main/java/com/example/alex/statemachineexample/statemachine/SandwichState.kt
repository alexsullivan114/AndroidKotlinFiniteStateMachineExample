package com.example.alex.statemachineexample.statemachine

import com.example.alex.statemachineexample.Sandwich
import com.example.alex.statemachineexample.SandwichType

/**
 * A [SandwichState] represents one state in our Sandwich state machine. It exposes a
 * [ViewState] value which represents how the view should be drawn while we're in this state.
 */
interface SandwichState {
  val viewState: ViewState
  fun consumeAction(action: Action): StateChange
}

/**
 * A container class for a new [SandwichState] and an emitted [Command]
 */
data class StateChange(val newState: SandwichState, val command: Command? = null)

/**
 * A [Command] represents some mutative work that should be done as a result of the state change -
 * any sort of network or database interaction could be represented through a [Command].
 */
sealed class Command {
  class SaveSandwich(val sandwich: Sandwich): Command()
}

/**
 * An [Action] is how the UI communicates to the state machine that something occurred - maybe it
 * was a user interaction, maybe a broadcast was received and so on.
 */
sealed class Action {
  class AddSandwichClicked : Action()
  class SandwichTypeSelected(val type: SandwichType): Action()
  class PredefinedSandwichSelected(val sandwich: Sandwich): Action()
  class SubmitSandwichClicked(val sandwichName: String): Action()
}

/**
 *  A [ViewState] describes how the UI should be drawn when in a given state of the state machine.
 *  It should include all relevant information for the UI.
 */
sealed class ViewState {
  class AddSandwichViewState : ViewState()
  class DescribeSandwichViewState : ViewState()
  class SandwichListViewState(val sandwiches: List<Sandwich>): ViewState()
}