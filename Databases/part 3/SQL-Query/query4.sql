SELECT
  genre,
  weekday,
  CASE 
    WHEN NOstudents = max_number_of_spots - 1 THEN 'One seat left'
    WHEN NOstudents = max_number_of_spots - 2 THEN 'Two seats left' 
    ELSE 'Many seats left'
  END AS numberoffreeseats
FROM (
  SELECT
    lesson.genre,
    TO_CHAR(lesson.date_of_taken_lesson, 'DAY') AS weekday,
    COUNT(student_lesson.student_id) AS NOstudents,
    lesson.max_number_of_spots
  FROM
    lesson
  JOIN
    student_lesson ON lesson.id = student_lesson.lesson_id
  WHERE
    lesson.lesson_type = 'Ensemble'
  GROUP BY
    lesson.genre, weekday, lesson.max_number_of_spots
) AS subquery

