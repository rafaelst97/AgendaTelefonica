package br.univali.pdm.agendatelefonica

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import androidx.room.Database
import br.univali.pdm.agendatelefonica.databinding.FragmentAdicionarContatoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AdicionarContatoFragment : Fragment() {

    private var _binding: FragmentAdicionarContatoBinding? = null
    private lateinit var database: DatabaseReference
    val tiposTelefone = arrayOf("Casa", "Celular", "Trabalho")
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_adicionar_contato, container, false)
        val spinner: Spinner = root.findViewById(R.id.spinnerTipoAdicionar)
        val salvar: Button = root.findViewById(R.id.botaoSalvarAdicionar)
        val cancelar: Button = root.findViewById(R.id.botaoCancelarAdicionar)
        var campoNome: EditText = root.findViewById(R.id.inputNomeAdicionar)
        var campoTelefone: EditText = root.findViewById(R.id.inputTelefoneAdicionar)
        var tipoTelefone: Spinner = root.findViewById(R.id.spinnerTipoAdicionar)

        context?.let { context ->
            ArrayAdapter.createFromResource(
                context,
                R.array.tipos_telefone,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        }

        salvar.setOnClickListener {
            val nome: String = campoNome.text.toString()
            val telefone: String = campoTelefone.text.toString()
            val tipo: String = tipoTelefone.selectedItem.toString()

            database = FirebaseDatabase.getInstance().getReference("Contatos")
            val Contato = Contato(nome, telefone, tipo)
            database.child(nome).setValue(Contato).addOnSuccessListener {

                campoNome.text.clear()
                campoTelefone.text.clear()
                tipoTelefone.adapter = null

            }

            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        cancelar.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        _binding = FragmentAdicionarContatoBinding.inflate(inflater, container, false)
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.botaoSalvarAdicionar.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.botaoCancelarAdicionar.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}