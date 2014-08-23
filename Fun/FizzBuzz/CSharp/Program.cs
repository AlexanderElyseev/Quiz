namespace FizzBuzz
{
    using System;

    class Program
    {
        public static void Main()
        {
            for (int i = 1; i <= 100; i++)
                // Custom numeric format:
                // #; -> number or emty string for zero ("#" sepcification)
                // ;;Txt -> Txt for zero value
                Console.Write(
                    "{0:#}{1:;;Fizz}{2:;;Buzz} ",
                    i % 3 * i % 5 == 0 ? 0 : i,
                    i % 3,
                    i % 5);

            Console.ReadKey();
        }
    }
}