SELECT person.name, instructor.id, COUNT(lesson.id) AS NOlessons 
FROM person
JOIN instructor ON person.id = instructor.id
JOIN lesson ON instructor.id = lesson.instructor_id
WHERE EXTRACT(MONTH FROM date_of_taken_lesson) = EXTRACT(MONTH FROM CURRENT_DATE)
GROUP BY person.name, instructor.id, EXTRACT(MONTH FROM date_of_taken_lesson)
ORDER BY person.name
