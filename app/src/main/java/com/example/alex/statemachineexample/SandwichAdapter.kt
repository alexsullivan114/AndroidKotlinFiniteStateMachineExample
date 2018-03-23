package com.example.alex.statemachineexample

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_sandwich.view.name
import kotlinx.android.synthetic.main.adapter_sandwich.view.sandwich_picture
import kotlinx.android.synthetic.main.adapter_sandwich.view.type

class SandwichAdapter(
  private val sandwiches: List<Sandwich>,
  private val clickListener: ((Sandwich) -> Unit)? = null
) : Adapter<SandwichViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SandwichViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_sandwich, parent, false)
    return SandwichViewHolder(view)
  }

  override fun getItemCount(): Int = sandwiches.size

  override fun onBindViewHolder(holder: SandwichViewHolder, position: Int) {
    val sandwich = sandwiches[position]
    holder.itemView.name.text = sandwich.name
    holder.itemView.type.text = sandwich.type.toString().toLowerCase()
    holder.itemView.setOnClickListener { clickListener?.invoke(sandwich) }
    holder.itemView.sandwich_picture.setImageResource(randomSandwichImage())
  }

  private fun randomSandwichImage(): Int {
    val images = listOf(
      R.drawable.sandwich_1,
      R.drawable.sandwich_2,
      R.drawable.sandwich_3
    )

    return images.shuffled().first()
  }
}

class SandwichViewHolder(view: View) : RecyclerView.ViewHolder(view)