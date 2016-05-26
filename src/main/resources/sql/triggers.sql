-- Триггер для вычисления оценки качества на основе оценок ожидания и восприятия

CREATE OR REPLACE FUNCTION eval_quality_mark()
  RETURNS trigger AS $$
DECLARE
	after_use_mark bigint;
	expectation_mark bigint;
BEGIN
	after_use_mark = NEW.after_use_mark_id;
	expectation_mark = NEW.expectation_mark_id;
	NEW.quality_mark_id := greatest(least(2 * after_use_mark - expectation_mark, 5), 1);
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER t_service_quality_survey_results
  BEFORE INSERT OR UPDATE
  ON service_quality_survey_results
  FOR EACH ROW
  EXECUTE PROCEDURE eval_quality_mark();