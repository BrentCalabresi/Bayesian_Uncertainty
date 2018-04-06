4/5/18
Project 3 - Uncertain Inference

Sam King - sking19@u.rochester.edu
Jeremy Atkins - jatkins5@u.rochester.edu
Brent Calabresi - bcalabre@u.rochester.edu

------------------------------------------------

To run, navigate to ~/Uncertain_Inference/src/
and type 'javac ApproximateInference.java && javac ExactInference.java' to compile

To test Exact Inference, type:
java ExactInference [file] [query] [evidence1] [evidence1 value] (etc. for all evidence)

Example:
java ExactInference aima-alarm.xml B J true M true

To test Approximate Inference, type:
java ApproximateInference [sample size] [file] [query] [evidence1] [evidence1 value] (etc. for all evidence)

Example:
java ApproximateInference 10000 aima-alarm.xml B J true M true
