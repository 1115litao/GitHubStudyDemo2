package adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotilndemo.R
import kotlinx.android.synthetic.main.music_item.view.*

/**
 * @description
 * @author      李涛
 * @version
 * @Date        2018/9/12.
 */

class MusicAdapter(private var context: Context?, private var dataList: ArrayList<String>) : RecyclerView.Adapter<MusicAdapter.Holder>(), View.OnClickListener {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.music_item,null)
        view.setOnClickListener(this)
            return Holder(view)
    }

    override fun getItemCount(): Int {
         return dataList.size
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
            holder!!.itemView.tag = position
            holder.musicTitle!!.text = dataList[position]
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       var musicTitle:TextView? = null

        init {
            musicTitle = itemView.item_music_name
        }
    }
    override fun onClick(v: View?) {
        itemOclick(v!!.tag as Int)
    }

    //设置Item的监听
    lateinit var itemOclick:(Int)->Unit
    fun setItemClickListener(itemOclick:(Int)->Unit){
        this.itemOclick = itemOclick
    }
}