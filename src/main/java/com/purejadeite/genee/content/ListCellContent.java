package com.purejadeite.genee.content;

import java.util.List;

import com.purejadeite.genee.definition.cell.ListCellDefinition;
import com.purejadeite.util.collection.Table;

/**
 * セルの値を保持するクラス
 * @author mitsuhiroseino
 */
public class ListCellContent extends AbstractListValueContent<ListCellDefinition> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5646504213059791683L;

	/**
	 * コンストラクタ
	 * @param parent 親コンテンツ
	 * @param definition 定義
	 */
	public ListCellContent(ParentContentInterface<?, ?, ?> parent, ListCellDefinition definition) {
		super(parent, definition);
	}

	@Override
	public int capture(Table<String> table) {
		Object value = getDefinition().capture(table);
		if (value instanceof List) {
			values.addAll((List<?>) value);
		} else {
			values.add(value);
		}
		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValuesImpl() {
		return definition.applyOptions(values, this);
	}

}
