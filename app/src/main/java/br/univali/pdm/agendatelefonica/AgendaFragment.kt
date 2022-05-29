package br.univali.pdm.agendatelefonica

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.univali.pdm.agendatelefonica.databinding.FragmentAgendaBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AgendaFragment : Fragment() {

    private var _binding: FragmentAgendaBinding? = null
    private lateinit var database: DatabaseReference
    private lateinit var contatoRecyclerView: RecyclerView
    private lateinit var contatoArrayList: ArrayList<Contato>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_agenda, container, false)
        val botaoAdicionar = root.findViewById<FloatingActionButton>(R.id.adicionarContato)
        contatoRecyclerView = root.findViewById(R.id.listaContatos)
        contatoRecyclerView.layoutManager = LinearLayoutManager(context)
        contatoRecyclerView.setHasFixedSize(true)

        contatoArrayList = arrayListOf<Contato>()
        getDadosContato()

        botaoAdicionar.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        _binding = FragmentAgendaBinding.inflate(inflater, container, false)
        return root

    }

    private fun getDadosContato() {

        database = FirebaseDatabase.getInstance().getReference("Contatos")
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (contatoSnapshot in snapshot.children){

                        val contato = contatoSnapshot.getValue(Contato::class.java)
                        contatoArrayList.add(contato!!)
                    }

                    contatoRecyclerView.adapter = AgendaAdapter(contatoArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.adicionarContato.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}