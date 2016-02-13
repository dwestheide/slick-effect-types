This is a tiny, over-simplified example demonstrating how to prevent at compile time that you are writing to your slave database, leveraging Slick's DB action effect types.

Please read my corresponding blog post, [Put your writes where your master is: Compile-time restriction of Slick effect types](http://danielwestheide.com/blog/2015/06/29/put-your-writes-where-your-master-is-compile-time-restriction-of-slick-effect-types.html) for more information.

## Usage

The main goal of this is to show how to provoke compile errors when trying to run undesired actions against a certain database. If you want to do more than compiling and actually run the application, you need to install H2 and run it in server mode. On OS X, with Homebrew, it is as simple as this:

```
brew install h2
h2
```
