package com.mikepenz.fastadapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Kotlin type alias to simplify usage for an all accepting item
 */
typealias GenericItem = IItem<out RecyclerView.ViewHolder>

/**
 * Created by mikepenz on 03.02.15.
 */
interface IItem<VH : RecyclerView.ViewHolder> : IIdentifyable {

    /** A Tag of the Item */
    var tag: Any?

    /** If the item is enabled */
    var isEnabled: Boolean

    /** If the item is selected */
    var isSelected: Boolean

    /** If the item is selectable */
    var isSelectable: Boolean

    /** The type of the Item. Can be a hardcoded INT, but preferred is a defined id */
    @get:IdRes
    val type: Int

    /** The layout for the given item */
    @get:LayoutRes
    val layoutRes: Int

    /** Generates a view by the defined LayoutRes */
    fun generateView(ctx: Context): View

    /** Generates a view by the defined LayoutRes and pass the LayoutParams from the parent */
    fun generateView(ctx: Context, parent: ViewGroup): View

    /** Generates a ViewHolder from this Item with the given parent */
    fun getViewHolder(parent: ViewGroup): VH

    /** Binds the data of this item to the given holder */
    fun bindView(holder: VH, payloads: MutableList<Any>)

    /** View needs to release resources when its recycled */
    fun unbindView(holder: VH)

    /** View got attached to the window */
    fun attachToWindow(holder: VH)

    /** View got detached from the window */
    fun detachFromWindow(holder: VH)

    /**
     * View is in a transient state and could not be recycled
     *
     * @param holder
     * @return return true if you want to recycle anyways (after clearing animations or so)
     */
    fun failedToRecycle(holder: VH): Boolean

    /** If this item equals to the given identifier */
    fun equals(id: Int): Boolean
}
