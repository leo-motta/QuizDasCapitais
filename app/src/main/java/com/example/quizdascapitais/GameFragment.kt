package com.example.quizdascapitais

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.quizdascapitais.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    data class Question(
            val text: String,
            val answers: List<String>)
    //A Primeira resposta é a correta
    private val questions: MutableList<Question> = mutableListOf(
	Question(text = "Qual a capital do Acre(AC)?",
                    answers = listOf("Rio Branco", "Macapá", "Manaus", "Boa Vista")),

	Question(text = "Qual a capital de Alagoas(AL)?",
                    answers = listOf("Maceío", "Macapá", "Rio Branco", "Boa Vista")),

	Question(text = "Qual a capital do Amapá(AP)?",
                    answers = listOf("Macapá", "Manaus", "Boa Vista", "Porto Velho")),

	Question(text = "Qual a capital de Amazonas(AM)?",
                    answers = listOf("Manaus", "Macapá", "Boa Vista", "Porto Velho")),

	Question(text = "Qual a capital da Bahia(BA)?",
                    answers = listOf("Salvador", "Fortaleza", "Recife", "Aracaju")),

	Question(text = "Qual a capital do Ceará(CE)?",
                    answers = listOf("Fortaleza", "Teresina", "Natal", "João Pessoa")),

	Question(text = "Qual a capital do Distrito Federal(DF)?",
                    answers = listOf("Brasília", "Cuiabá", "Campo Grande", "Goiânia")),

	Question(text = "Qual a capital do Espírito Santo(ES)?",
                    answers = listOf("Vitória", "Belo Horizonte", "Rio de Janeiro", "São Paulo")),

	Question(text = "Qual a capital de Goiás(GO)?",
                    answers = listOf("Goiânia", "Cuiabá", "Campo Grande", "Brasília")),

	Question(text = "Qual a capital de Maranhão(MA)?",
                    answers = listOf("São Luís", "João Pessoa", "Teresina", "Natal")),

	Question(text = "Qual a capital do Mato Grosso(MT)?",
                    answers = listOf("Cuiabá", "Goiânia", "Campo Grande", "Brasília")),

	Question(text = "Qual a capital do Mato Grosso do Sul(MS)?",
                    answers = listOf("Campo Grande", "Cuiabá", "Goiânia", "Brasília")),

	Question(text = "Qual a capital de Minas Gerais(MG)?",
                    answers = listOf("Belo Horizonte", "Vitória", "Rio de Janeiro", "São Paulo")),

	Question(text = "Qual a capital do Pará(PA)?",
                    answers = listOf("Belém", "Porto Velho", "Boa Vista", "Macapá")),

	Question(text = "Qual a capital da Paraíba(PB)?",
                    answers = listOf("João Pessoa", "São Luís", "Aracaju", "Natal")),

	Question(text = "Qual a capital do Paraná(PR)?",
                    answers = listOf("Curitiba", "Florianópolis", "Porto Alegre", "Natal")),

	Question(text = "Qual a capital de Pernambuco(PE)?",
                    answers = listOf("Recife", "Fortaleza", "Teresina", "Aracaju")),

	Question(text = "Qual a capital do Piauí(PI)?",
                    answers = listOf("Teresina", "Fortaleza", "Recife", "Aracaju")),

	Question(text = "Qual a capital do Rio de Janeiro(RJ)?",
                    answers = listOf("Rio de Janeiro", "Vitória", "Belo Horizonte", "São Paulo")),

	Question(text = "Qual a capital do Rio Grande do Norte(RN)?",
                    answers = listOf("Natal", "João Pessoa", "Fortaleza", "Maceió")),

	Question(text = "Qual a capital do Rio Grande do Sul(RS)?",
                    answers = listOf("Porto Alegre", "Florianópolis", "Curitiba", "Teresina")),

	Question(text = "Qual a capital de Rondônia(RO)?",
                    answers = listOf("Porto Velho", "Boa Vista", "Macapá", "Rio Branco")),

	Question(text = "Qual a capital de Roraima(RR)?",
                    answers = listOf("Boa Vista", "Porto Velho", "Macapá", "Palmas")),

	Question(text = "Qual a capital de Santa Catarina(SC)?",
                    answers = listOf("Florianópolis", "Curitiba", "Porto Alegre", "Recife")),

	Question(text = "Qual a capital de São Paulo(SP)?",
                    answers = listOf("São Paulo", "Rio de Janeiro", "Belo Horizonte", "Vitória")),

	Question(text = "Qual a capital de Sergipe(SE)?",
                    answers = listOf("Aracaju", "Natal", "Teresina", "Recife")),

	Question(text = "Qual a capital de Tocantins(TO)?",
                    answers = listOf("Palmas", "Boa Vista", "Porto Velho", "Belém"))
    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = 27

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //Inflar o layout
        val binding: FragmentGameBinding = DataBindingUtil.inflate<FragmentGameBinding>(inflater, R.layout.fragment_game, container, false)
        //"Sorteia" as questões de forma aleatória
        randomizeQuestions()
        //Binding do jogo
        binding.game = this
        //Evento para quando o botão enviar é pressionado
        binding.submitButton.setOnClickListener { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            //Faz nada quando nenhum for selecionado(id = -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }
                //Roda se a resposta escolhida for igual a resposta certa(primeira resposta do "vetor")
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++
                    //Vai para a próxima questão
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        //Vai para a tela de vitória
                        view.findNavController().navigate(R.id.action_gameFragment_to_gameWonFragment)
                    }
                } else {
                    //Vai para a tela de derrota
                    view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                }
            }
        }
        return binding.root
    }
    //"Sorteia" as questões de forma aleatória
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    //Seleciona a proxima questão e "embaralha" as respostas
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.titulo, questionIndex + 1, numQuestions)
    }
}