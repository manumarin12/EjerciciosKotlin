import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.movil.ejercicio02.R
import com.movil.ejercicio02.Villano

class AdapterLibros(private val data: List<Villano>) : RecyclerView.Adapter<AdapterLibros.LibrosViewHolder>() {

    class LibrosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val txtname = itemView.findViewById<TextView>(R.id.txtName)
        private val txtgender = itemView.findViewById<TextView>(R.id.txtGender)
        private val txtstatus = itemView.findViewById<TextView>(R.id.txtStatus)


        fun bind(villano: Villano) {
            txtname.text = villano.name
            txtgender.text = villano.gender
            txtstatus.text = villano.status


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibrosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_libros,parent,false)
        return LibrosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: LibrosViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }
}
