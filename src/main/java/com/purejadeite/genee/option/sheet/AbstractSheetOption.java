package com.purejadeite.genee.option.sheet;

import java.io.Serializable;
import java.util.Map;

import com.purejadeite.genee.content.ContentInterface;
import com.purejadeite.genee.content.SpecificValue;
import com.purejadeite.genee.definition.DefinitionInterface;
import com.purejadeite.genee.option.AbstractOption;

/**
 * Sheetの値を変換する抽象クラス
 *
 * @author mitsuhiroseino
 */
abstract public class AbstractSheetOption extends AbstractOption implements SheetOptionInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4673665478166090604L;

	/**
	 * コンストラクタ
	 */
	public AbstractSheetOption(DefinitionInterface<?> definition) {
		super(definition);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object apply(Object values, ContentInterface<?, ?> content) {
		if (values == SpecificValue.UNDEFINED) {
			return values;
		}
		return applyImpl((Map<String, Object>) values, content);
	}

	/**
	 * テーブルの変換を行います
	 *
	 * @param values
	 *            変換前のテーブル
	 * @return 変換後のテーブル
	 */
	abstract protected Object applyImpl(Map<String, Object> values, ContentInterface<?, ?> content);

	public Map<String, Object> toMap() {
		Map<String, Object> map = super.toMap();
		map.put("name", this.getClass().getSimpleName());
		return map;
	}
}
