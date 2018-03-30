package com.example.alex.statemachineexample

interface SandwichView {
  fun showSandwichList(sandwiches: List<Sandwich>)
  fun hideSandwichList()

  fun showAddSandwichView(predefinedSandwiches: List<Sandwich>)
  fun hideAddSandwichView()

  fun showSandwichInputFields()
  fun hideSandwichInputFields()
}

interface SandwichPresenter {
  fun addSandwichClicked()
  fun sandwichTypeClicked(type: SandwichType)
  fun predefinedSandwichClicked(sandwich: Sandwich)
  fun submitSandwichClicked(sandwichName: String)
}