DELIMITER $$ 
CREATE PROCEDURE SP_S_Avaliado
(
	IN avaliado_id INT
)
BEGIN
	IF avaliado_id IS NOT NULL
	THEN
		SELECT 
			AV.avaliado_id,
			AV.nome,
			AV.data_nascimento,
			AV.sexo,
			AV.email,
			AV.CPF,
			AV.ativo
		FROM hugolutke01.Avaliado AS AV
		WHERE AV.avaliado_id = avaliado_id;	
	
    ELSE
		SELECT 
			AV.avaliado_id,
			AV.nome,
			AV.data_nascimento,
			AV.sexo,
			AV.email,
			AV.CPF,
			AV.ativo
		FROM hugolutke01.Avaliado AS AV;
	END IF;
END$$
DELIMITER ;