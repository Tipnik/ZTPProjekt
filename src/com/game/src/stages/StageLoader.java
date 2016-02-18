package com.game.src.stages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.game.src.entity.*;
import com.game.src.entity.enemies.*;
import com.game.src.main.GameMediator;
import com.game.src.stages.builder.*;

public class StageLoader {

	private IBoardBuilder builder;

	public StageLoader() {
		builder = new BoardBuilderForest();
	}

	// private Field field;
	public void setBoardBuilder(IBoardBuilder builder) {
		this.builder = builder;
	}

	public Stage loadStage(String path) throws FileNotFoundException, IOException {
		
		Stage stage = new Stage();

		File file = new File(path);

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			/// Field field = null;
			//// Enemy tempEnemy = null;
			int lineCount = 0;

			while ((line = br.readLine()) != null) {

				if (line.length() == 1) {
					builder.init();
					builder.SetNumber(((int) line.charAt(0) - 48));
					lineCount = 0;
					continue;
				}

				// i - x'sy
				for (int i = 0; i < line.length(); i++) {

					switch (line.charAt(i)) {
					case '#':
						builder.addSegment(i, lineCount);
						break;

					case 'W':
						builder.addSegmentW(i, lineCount);
						break;

					case 'L':
						builder.addSegmentL(i, lineCount);
						break;

					case 'C':
						builder.addSegmentC(i, lineCount);
						break;

					case 'D':
						builder.addSegmentD(i, lineCount);
						break;

					case 'P':
						builder.addSegmentP(i, lineCount);
						break;

					case 'F':
						builder.addSegmentF(i, lineCount);
						break;

					case 'M':
						builder.addSegmentM(i, lineCount);
						break;
						
					case 'B':
						builder.addSegmentB(i, lineCount);
						break;

					case 'Y':
						stage.setStartX(i);
						stage.setStartY(lineCount);
						
					case ' ':
						builder.addSegmentG(i, lineCount);
						break;

					default: {
						if (Character.isDigit(line.charAt(i))) {

							builder.addScreenChange(i, lineCount, (int) line.charAt(i) - 48);

						}

					}
						break;
					}

					// field.setObject(i, lineCount, tempEntity);

				}

				if (lineCount == 14) {
					stage.addField(builder.getField());
					lineCount = 0;
				} else
					lineCount++;

			}

		}
		return stage;

	}

}
