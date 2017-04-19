package kamedon.com.imageprocessingsample.page

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kamedon.com.imageprocessingsample.page.collision.circle.CollisionCircleActivity
import kamedon.com.imageprocessingsample.page.collision.square.CollisionSquareActivity
import kamedon.com.imageprocessingsample.page.edit.EditActivity
import kamedon.com.imageprocessingsample.page.frame.FrameActivity
import kamedon.com.imageprocessingsample.page.rotation.RotationActivity
import kamedon.com.imageprocessingsample.page.translation.TranslationActivity
import kotlin.reflect.KClass
import android.widget.TextView
import kamedon.com.imageprocessingsample.R


/**
 * Created by kamei.hidetoshi on 2017/04/19.
 */

sealed class Page(val activity: Class<out Activity>) {
    fun title() = activity.simpleName
}

object RotationPage : Page(RotationActivity::class.java)
object TranslationPage : Page(TranslationActivity::class.java)
object CollisionCirclePage : Page(CollisionCircleActivity::class.java)
object CollisionSquarePage : Page(CollisionSquareActivity::class.java)
object FramePage : Page(FrameActivity::class.java)
object EditPage : Page(EditActivity::class.java)


class PageAdapter(val context: Context) : RecyclerView.Adapter<PageAdapter.ViewHolder>() {
    val pages = listOf(RotationPage, TranslationPage, CollisionCirclePage, CollisionSquarePage, FramePage, EditPage)
    val inflater = LayoutInflater.from(context)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pages[position])
        holder.textView.setOnClickListener { context.startActivity(Intent(context, pages[position].activity)) }
    }

    override fun getItemCount(): Int = pages.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(inflater.inflate(R.layout.list_page, parent, false))


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView: Button = itemView.findViewById(R.id.text) as Button

        fun bind(page: Page) {
            textView.text = page.title()
        }

    }
}