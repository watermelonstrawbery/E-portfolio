
SELECT
  TO_CHAR(lesson.date_of_taken_lesson, 'MONTH') AS month_,
  COUNT(*) AS total,
  SUM(CASE WHEN lesson_type = 'Individual' THEN 1 ELSE 0 END) AS individual_lessons,
  SUM(CASE WHEN lesson_type = 'Group' THEN 1 ELSE 0 END) AS group_lessons,
  SUM(CASE WHEN lesson_type = 'Ensemble' THEN 1 ELSE 0 END) AS ensemble_lessons
FROM
  lesson
GROUP BY
  month_
ORDER BY
  month_;
