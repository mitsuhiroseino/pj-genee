package com.purejadeite.genee.option.cell;

import java.util.Map;

import com.purejadeite.genee.definition.DefinitionInterface;

/**
 * 指定された区切り文字で文字列を分割した最後の要素を取るオプション
 *
 * @author mitsuhiroseino
 *
 */
public class Last extends AbstractPositionCellOption {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5499433751785620491L;

	/**
	 * コンストラクタ
	 *
	 * @param cell
	 *            値の取得元Cell読み込み定義
	 * @param config
	 *            コンバーターのコンフィグ
	 */
	public Last(DefinitionInterface<?> definition, Map<String, Object> config) {
		super(definition, config);
	}

	@Override
	protected String get(String[] values) {
		return values[values.length - 1];
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = super.toMap();
		return map;
	}
}
