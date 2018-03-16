package com.example.alex.statemachineexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.alex.statemachineexample.SandwichType.GRINDER
import com.example.alex.statemachineexample.SandwichType.WRAP
import com.example.alex.statemachineexample.statemachine.Action
import kotlinx.android.synthetic.main.activity_sandwhich.add_sandwich
import kotlinx.android.synthetic.main.activity_sandwhich.add_sandwich_button
import kotlinx.android.synthetic.main.activity_sandwhich.added_sandwich_list
import kotlinx.android.synthetic.main.activity_sandwhich.grinder_button
import kotlinx.android.synthetic.main.activity_sandwhich.predefined_sandwich_list
import kotlinx.android.synthetic.main.activity_sandwhich.sandwich_inputs
import kotlinx.android.synthetic.main.activity_sandwhich.sandwich_list_container
import kotlinx.android.synthetic.main.activity_sandwhich.sandwich_name
import kotlinx.android.synthetic.main.activity_sandwhich.submit_button
import kotlinx.android.synthetic.main.activity_sandwhich.wrap_button

class SandwichActivity : AppCompatActivity(), SandwichView {

  private lateinit var presenter: SandwichPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sandwhich)
    presenter = SandwichPresenter(this)
  }

  override fun showSandwichList(sandwiches: List<Sandwich>) {
    sandwich_list_container.visibility = View.VISIBLE
    added_sandwich_list.adapter = SandwichAdapter(sandwiches)
    add_sandwich_button.setOnClickListener { presenter.acceptAction(Action.AddSandwichClicked()) }
  }

  override fun hideSandwichList() {
    sandwich_list_container.visibility = View.GONE
  }

  override fun showAddSandwichView(predefinedSandwiches: List<Sandwich>) {
    add_sandwich.visibility = View.VISIBLE
    wrap_button.setOnClickListener {
      presenter.acceptAction(Action.SandwichTypeSelected(WRAP))
    }
    grinder_button.setOnClickListener {
      presenter.acceptAction(Action.SandwichTypeSelected(GRINDER))
    }
    predefined_sandwich_list.adapter = SandwichAdapter(predefinedSandwiches, {
      presenter.acceptAction(Action.PredefinedSandwichSelected(it))
    })
  }

  override fun hideAddSandwichView() {
    add_sandwich.visibility = View.GONE
  }

  override fun showSandwichInputFields() {
    sandwich_inputs.visibility = View.VISIBLE
    submit_button.setOnClickListener {
      presenter.acceptAction(Action.SubmitSandwichClicked(sandwich_name.text.toString()))
    }
  }

  override fun hideSandwichInputFields() {
    sandwich_inputs.visibility = View.GONE
  }
}
