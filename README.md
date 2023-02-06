# TextGen
- Generates text that follows the same style as a given text file.
- Changing the order value changes how many previous words the model will use to predict the next words.
- Loads the text into a TextModel object. Cleans the text by getting rid of whitespace and splitting into individual words.
- Using a HashMap, the TextModel saves how many times different words appear after the previous n words.
- The tester generates 300 new words by randomly choosing a word that appears after the last n words, which is weighted based on frequency.