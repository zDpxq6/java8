package ch03.exercise10;

/* 10. なぜ, 次の呼び出しができないしょうか.
 * UnaryOperator op = Collor::brighter;
 * Image finalImage = transform(image, op.compose(Color::grayscale));
 *
 * UnaryOperator<T>のcomposeメソッドの戻り値型を注意深く調べなさい.
 * なぜ, transformメソッドに対しては適切ではないのでしょうか.
 * 関数合成に関しては,
 * ストラクチャル型(structual type)とノミナル型(nominal tyoe)のユーティリティに関して, 何が言えますか.
*/

public class Snipet{}