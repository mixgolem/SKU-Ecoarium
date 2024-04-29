import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.ecoariumapp.IpConfig
import com.example.ecoariumapp.R

class RecyclerViewAdapter(private val itemList: List<List<String>>) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ItemImage: ImageView = view.findViewById(R.id.itemImage)
        val ItemName: TextView = view.findViewById(R.id.itemName)
        val ItemMessage: TextView = view.findViewById(R.id.itemMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val itemData = itemList[position]

        // Assuming that the second element in the list is the item message
        val itemMessage = itemData[1]
        holder.ItemMessage.text = itemMessage

        // Assuming that the third element in the list is the item name
        val itemName = itemData[2]
        holder.ItemName.text = itemName

        // Assuming that the fourth element in the list is the item image file name
        val itemImageFileName = itemData[3]
        val itemImageURL = "http://${IpConfig.serverIp}:8000/uploads/$itemImageFileName"
        Glide.with(holder.ItemImage.context)
            .load(itemImageURL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    Log.e("Glide", "Image load failed", e)
                    return false
                }
                override fun onResourceReady(resource: Drawable?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    Log.d("Glide", "Image load success")
                    return false
                }
            })
            .into(holder.ItemImage)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}