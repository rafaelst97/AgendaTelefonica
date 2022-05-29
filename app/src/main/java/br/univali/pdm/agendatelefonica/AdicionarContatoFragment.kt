package br.univali.pdm.agendatelefonica

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import br.univali.pdm.agendatelefonica.databinding.FragmentAdicionarContatoBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AdicionarContatoFragment : Fragment() {

    private var _binding: FragmentAdicionarContatoBinding? = null
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