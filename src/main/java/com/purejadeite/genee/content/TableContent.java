package com.purejadeite.genee.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.purejadeite.genee.definition.table.TableDefinitionInterface;
import com.purejadeite.util.collection.Table;

/**
 * テーブル形式の範囲の情報を保持するクラスの抽象クラスです
 *
 * @author mitsuhiroseino
 */
public class TableContent extends
		AbstractParentContent<ParentContentInterface<?, ?, ?>, TableCellContentInterface<?>, TableDefinitionInterface<?>>implements TableContentInterface {

	private static final long serialVersionUID = 2393648151533807595L;

	/**
	 * レコード数
	 */
	protected int size = 0;

	private boolean begin = false;

	/**
	 * コンストラクタ
	 */
	public TableContent(ParentContentInterface<?, ?, ?> parent, TableDefinitionInterface<?> definition) {
		super(parent, definition);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getRawValuesImpl() {
		return getCellValues(new CellValueGetter() {
			@Override
			public Object get(TableCellContentInterface<?> cell) {
				return cell.getRawValues();
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValuesImpl() {
		Object values = getCellValues(new CellValueGetter() {
			@Override
			public Object get(TableCellContentInterface<?> cell) {
				return cell.getValues();
			}
		});
		return definition.applyOptions(values, this);
	}

	/**
	 * 値取得を行う共通処理
	 *
	 * @param getter
	 *            Getterの実装
	 * @param ignore
	 *            取得しない定義
	 * @return 値
	 */
	private Object getCellValues(CellValueGetter getter) {
		List<Map<String, Object>> values = new ArrayList<>(size);

		for (TableCellContentInterface<?> cell : children) {
			@SuppressWarnings("unchecked")
			List<Object> vals = (List<Object>) getter.get(cell);
			if (vals == null || SpecificValue.NO_OUTPUT.equals(vals)) {
				continue;
			}
			for (int i = 0; i < vals.size(); i++) {
				Object val = vals.get(i);
				Map<String, Object> record = null;
				if (values.size() < i + 1) {
					record = new HashMap<>();
					values.add(record);
				} else {
					record = values.get(i);
				}
				if (!SpecificValue.UNDEFINED.equals(val)) {
					record.put(cell.getName(), val);
				}
			}
		}
		return values;
	}

	/**
	 * 値取得処理の共有用インターフェイス
	 *
	 * @author mitsuhiroseino
	 */
	private static interface CellValueGetter {
		/**
		 * cellから値を取得します
		 *
		 * @param cell
		 *            Cell読み込み情報
		 * @return 値
		 */
		public Object get(TableCellContentInterface<?> cell);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = super.toMap();
		List<Map<String, Object>> cellMaps = new ArrayList<>();
		for (ContentInterface<?, ?> cell : children) {
			cellMaps.add(cell.toMap());
		}
		map.put("cells", cellMaps);
		map.put("begin", begin);
		return map;
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TableContentInterface getTable() {
		return this;
	}

	@Override
	public int capture(Table<String> table) {
		int size = 0;
		int keySize = -1;
		for (TableCellContentInterface<?> child : getChildren()) {
			if (child.getDefinition().isBreakKey()) {
				// キーのみ先に処理
				keySize = child.capture(table);
				size = keySize;
			}
		}
		for (TableCellContentInterface<?> child : getChildren()) {
			if (child.getDefinition().isBreakKey()) {
				// キーは処理済み
				continue;
			}
			size += child.capture(table, keySize);
		}
		return size;
	}

}
