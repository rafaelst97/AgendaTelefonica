package br.univali.pdm.agendatelefonica.Adapter

import android.app.Activity
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import br.univali.pdm.agendatelefonica.R
import br.univali.pdm.agendatelefonica.model.Contato

class ListContatoAdapter(internal var activity: Activity,
                         internal var contatos:List<Contato>,
                         internal var edit_id:EditText,
                         internal var edit_nome:EditText,
                         internal var edit_telefone:EditText,
                         internal var edit_tipo:EditText): BaseAdapter() {

    internal var inflater:LayoutInflater

    init {
        inflater = activity.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
    override fun getCount(): Int {
        return contatos.size
    }

    override fun getItem(posicao: Int): Any {
        contatos[posicao]
    }

    override fun getItemId(posicao: Int): Long {
        return contatos[posicao].id.toLong()
    }

    override fun getView(posicao: Int, p1: View?, p2: ViewGroup?): View {
        val rowView:View
        rowView = inflater.inflate(R.layout.fragment_adicionar_contato, null)

        rowView.txt_row_id = contatos[posicao].id.toString()
    }
}