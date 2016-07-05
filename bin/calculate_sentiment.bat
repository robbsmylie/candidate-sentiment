echo "starting calculate sentiment batch file"
REM java -cp ../target/candidate-sentiment-0.0.1-SNAPSHOT.jar;../target/dependency/* org.smylie.spike.candidatesentiment.CalculateSentiment
curl http://localhost:8080/calculate/sentiment