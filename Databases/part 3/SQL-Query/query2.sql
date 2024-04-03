 SELECT
    num_siblings,
    COUNT(student_id) AS num_students
FROM (
    SELECT
        student.id AS student_id,
        COUNT(student_sibling.sibling_id) AS num_siblings
    FROM
        student 
    LEFT JOIN
        student_sibling ON student.id = student_sibling.student_id
    GROUP BY
        student.id
) AS student_siblings_count
WHERE
    num_siblings <= 2
GROUP BY
    num_siblings
ORDER BY
    num_siblings;

