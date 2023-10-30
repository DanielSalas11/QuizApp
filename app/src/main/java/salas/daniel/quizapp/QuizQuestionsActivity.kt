package salas.daniel.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mUserName: String? = null
    private var mCorrectAnswers: Int = 0
    private var canAnswer: Boolean = true

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var btnSubmit: Button? = null

    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)


        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)


        tvOptionOne = findViewById(R.id.tv_Option_One)
        tvOptionTwo = findViewById(R.id.tv_Option_Two)
        tvOptionThree = findViewById(R.id.tv_Option_Three)
        tvOptionFour = findViewById(R.id.tv_Option_Four)

        btnSubmit = findViewById(R.id.btn_submit)

        mQuestionsList = Constants.getQuestions()
        progressBar?.max = mQuestionsList!!.size

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        setQuestion()

    }

    //This method sets the questionon the activity_quiz_questions xml.
    private fun setQuestion() {
        canAnswer = true
        defaultOptionsView()
        btnSubmit?.text = "Skip"
        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        ivImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour
    }

    //Makes all the TextViews be on a non-clicked regular status
    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        btnSubmit?.text = "Valider"
        tvOptionOne?.let {
            options.add(0, it)
        }
        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    //Allows to select an option and give a visual indicator of it
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    //Checks if the question can be answered and selects it with the selectedOptionView(), also allows to submit answers
    override fun onClick(view: View?) {
        if (canAnswer) {
            when (view?.id) {
                R.id.tv_Option_One -> {
                    tvOptionOne?.let {
                        selectedOptionView(it, 1)
                    }
                }
                R.id.tv_Option_Two -> {
                    tvOptionTwo?.let {
                        selectedOptionView(it, 2)
                    }
                }
                R.id.tv_Option_Three -> {
                    tvOptionThree?.let {
                        selectedOptionView(it, 3)
                    }
                }

                R.id.tv_Option_Four -> {
                    tvOptionFour?.let {
                        selectedOptionView(it, 4)
                    }
                }
            }
        }
        if (view?.id == R.id.btn_submit) {
            if (mSelectedOptionPosition == 0) {
                Log.e("mCurrentPosition:", "$mCurrentPosition")
                if (mCurrentPosition < mQuestionsList!!.size) {
                    mCurrentPosition++
                } else {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra(Constants.USER_NAME, mUserName)
                    intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                    intent.putExtra(Constants.TOTAL_QUESTION, mQuestionsList?.size)
                    startActivity(intent)
                    finish()
                }
                when {
                    mCurrentPosition <= mQuestionsList!!.size -> {
                        setQuestion()
                    }
                }
            } else {
                Log.e("mCurrentPosition:", "$mCurrentPosition")
                val question = mQuestionsList?.get(mCurrentPosition - 1)
                if (question!!.correctAnswer != mSelectedOptionPosition) {
                    answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                } else {
                    mCorrectAnswers++
                }
                answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                if (mCurrentPosition == mQuestionsList!!.size) {
                    btnSubmit?.text = "Terminer"
                } else {
                    btnSubmit?.text = "Continuer"
                }

                mSelectedOptionPosition = 0
            }
        }
    }

    //Checks answers
    private fun answerView(answer: Int, drawableView: Int){
        canAnswer = false
        when(answer){
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}