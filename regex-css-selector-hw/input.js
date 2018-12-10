regExps = {
"exercise_1": /[A-Z][a-z]+/,
"exercise_2": /08[1-9]{8}/,
"exercise_3": /[^01]+/,
"exercise_4": /^[^\d\W_][^$@]{2,30}$/,
"exercise_5": /^[^2]\d+[^1]$/,
"exercise_6": /class=["'].+['"]/
};
cssSelectors = {
"exercise_1": "item>java:first-of-type",
"exercise_2": "different * *",
"exercise_3": "item>.class1 tag",
"exercise_4": "css>item:nth-of-type(3)",
"exercise_5": "item>tag java:nth-child(2)",
"exercise_6": "#someId item item item:last-child item",
"exercise_7": "different #diffId2 java:last-child",
"exercise_8": "#someId"
};
