package be.adarbitrium.model.latin_quizz;

import java.time.LocalDate;

public class ExamStat {
	private final int mUserId;
	private final int mDuree; // en secondes
	private final int mResult;
	private final LocalDate mDate;
	private boolean[] mCategoriesDecl = new boolean[9]; // PP PAD A2 A1 N5 N4 N3 N2 N1
	private int mCategoriesDeclBinary; //représentation binaire de categoriesMots (pour enregistrement en db)

	public ExamStat(int userId, int duree, int result, boolean[] categories) {
		mUserId = userId;
		mDuree = duree;
		mResult = result;
		mCategoriesDecl = categories;
		mDate = LocalDate.now();
		categoriesToBinary();
	}

	public ExamStat(int userId, LocalDate date, int duree, int result, int binary) {
		mUserId = userId;
		mDuree = duree;
		mResult = result;
		mCategoriesDeclBinary = binary;
		mDate = date;
		binaryToCategories();
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return mDate;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return mUserId;
	}

	/**
	 * @return the duree
	 */
	public int getDuree() {
		return mDuree;
	}

	/**
	 * @return the result
	 */
	public int getResult() {
		return mResult;
	}

	/**
	 * @return the categoriesDecl
	 */
	public boolean[] getCategoriesDecl() {
		return mCategoriesDecl;
	}

	/**
	 * @return the categoriesDeclBinary
	 */
	public int getCategoriesDeclBinary() {
		return mCategoriesDeclBinary;
	}

	private void categoriesToBinary() {
		mCategoriesDeclBinary = 0;
		int a = 0;
		for (boolean b : mCategoriesDecl) {
			if (b) mCategoriesDeclBinary += 2^a;
			a++;
		}
		System.out.println(mCategoriesDecl.toString() + " to: " + Integer.toBinaryString(mCategoriesDeclBinary));
	}
	
	private void binaryToCategories() {
		int binary = mCategoriesDeclBinary;
		int a = mCategoriesDecl.length;
		do {
			a-=1;
			int bit = Math.floorDiv(binary, 2^a);
			if (bit == 1) mCategoriesDecl[a] = true;
			else mCategoriesDecl[a] = false;
			binary -= bit;
		} while (a>0);
		System.out.println(mCategoriesDecl.toString() + " from: " + Integer.toBinaryString(mCategoriesDeclBinary));

	}
}

