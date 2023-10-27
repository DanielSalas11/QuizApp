package salas.daniel.quizapp

object Constants {

    fun getQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1, "Oú se trouve la Tour Eiffel?",
            R.drawable.tour_eiffel,
            optionOne = "Bordeaux",
            optionTwo = "Ciudad Obregón",
            optionThree = "Paris",
            optionFour = "Marseille",
            correctAnswer = 3
        )
        questionsList.add(que1)
        val que2 = Question(
            1, "Quel est le tableau le plus célèbre du Louvre?",
            R.drawable.musee_du_louvre,
            optionOne = "La Liberté guidant le peuple",
            optionTwo = "La Joconde",
            optionThree = "Le Radeau de la Méduse",
            optionFour = "Le Sacre de Napoléon",
            correctAnswer = 2
        )
        questionsList.add(que2)
        val que3 = Question(
            1, "Comment s'apelle ce monument?",
            R.drawable.l_arc_de_triomphe,
            optionOne = "Tour Eiffel",
            optionTwo = "Le Panthéon",
            optionThree = "Église Notre-Dame",
            optionFour = "L'Arc de Triomphe",
            correctAnswer = 4
        )
        questionsList.add(que3)

        val que4 = Question(
            1, "Quelle ville est-il?",
            R.drawable.marseille,
            optionOne = "Marseille",
            optionTwo = "Strasbourg",
            optionThree = "Toulouse",
            optionFour = "Lyon",
            correctAnswer = 1
        )
        questionsList.add(que4)

        val que5 = Question(
            1, "On le trouve dans quel arrondissement à Paris?",
            R.drawable.le_pantheon,
            optionOne = "2e arrondissement",
            optionTwo = "5e arrondissement",
            optionThree = "9e arrondissement",
            optionFour = "20e arrondissement",
            correctAnswer = 2
        )
        questionsList.add(que5)

        val que6 = Question(
            1, "Comment s'apelle ce monument?",
            R.drawable.eglise_notre_dame,
            optionOne = "Église Notre-Dame",
            optionTwo = "Tour Eiffel",
            optionThree = "Le Panthéon",
            optionFour = "Musée du Louvre",
            correctAnswer = 1
        )
        questionsList.add(que6)

        return questionsList
    }
}