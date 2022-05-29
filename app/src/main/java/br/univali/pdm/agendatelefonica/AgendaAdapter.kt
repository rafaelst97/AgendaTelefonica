package br.univali.pdm.agendatelefonica

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AgendaAdapter(private val contatoLista: ArrayList<Contato>) : RecyclerView.Adapter<AgendaAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.contato_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = contatoLista[position]

        holder.nome.text = currentItem.nome
        holder.telefone.text = currentItem.telefone
    }

    override fun getItemCount(): Int {
        return contatoLista.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val nome : TextView = itemView.findViewById(R.id.nomeContato)
        val telefone: TextView = itemView.findViewById(R.id.telefoneContato)

    }
}