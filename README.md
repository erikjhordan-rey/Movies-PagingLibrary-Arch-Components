 # Paging Library - Architecture Components

Sample created to practice **Paging Library**.

## What Have I learned?

* **Paging Library:** The Paging Library makes it easier for you to load data on demand within your app's RecyclerView.
  
* **PagedList:** Collection that loads data in pages, asynchronously. A PagedList can be used to load data from sources you define, and present it easily in your UI with a RecyclerView.
    
* **DataSource and DataSource.Factory:** DataSource is the base class for loading snapshots of data into a PagedList. A DataSource.Factory is used to create a DataSource.

* **PageKeyedDataSource:** DataSource used to load embed next/previous keys.

* **ItemKeyedDataSource:** DataSource used to retrieve data from item N to fetch item N+1

* **PositionalDataSource:** if you need to fetch pages of data from any location you choose.

* **LivePagedListBuilder:** Class used to build a LiveData<PagedList> based on DataSource.Factory and a PagedList.Config.
  
* **RxPagedListBuilder:** Class used to build a Observable<PagedList> based on DataSource.Factory and a PagedList.Config.
  
* **BoundaryCallback:** Helper callback to signals when a PagedList has reached the end of available data.
    
* **PagedListAdapter:** RecyclerView.Adapter that presents paged data from PagedLists in a RecyclerView. PagedListAdapter listens to PagedList loading callbacks as pages are loaded, and uses DiffUtil to compute fine grained updates as new PagedLists are received.

## What was wrong?
 
 * I get stranger behaviors with LinearLayoutManager & GridLayoutManager  `onItemAtEndLoaded` is called when I'm not scrolling    to the end of list.
 * Handle errors, implies use LiveData observables in DataLayer but I prefer keep working with RX and LiveData only for UI layer.
 * Limited API to work on Rx architectures, I think there should be a reactive version of `PagedList.BoundaryCallback<Movie>()`
 * It's alpha so I won't use it in production in a near future
 * Could save a lot of time handling the logic to retrieve data although be forced to use pager library elements like `PagedList` & `PagedListAdapter`
 
I do like the library but It is not time to use it in production as it does not fit with the architecture we have today.

# Demo

|<img src="https://user-images.githubusercontent.com/5893477/39963467-36e02f3e-5631-11e8-8c0a-37739485c7d4.png" width="260"> |<img src="./art/movies_.gif" width="260"> |

### Resources to start with Paging Library on Android

* [Paging library overview Android by Google][10]
* [Paging Library CodeLab][11]
* [PagingWithNetworkSample by Yigit Boyar][12]
* [Android Jetpack: manage infinite lists with RecyclerView and Paging (Google I/O '18)][13]
* [Android Jetpack: what's new in Architecture Components (Google I/O '18)][13]

 [9]: https://developer.android.com/topic/libraries/architecture/paging/
[10]: https://codelabs.developers.google.com/codelabs/android-paging/index.html?index=..%2F..%2Findex#0
[11]: https://github.com/googlesamples/android-architecture-components/tree/master/PagingWithNetworkSample
[12]: https://www.youtube.com/watch?v=BE5bsyGGLf4
[13]: https://www.youtube.com/watch?v=pErTyQpA390&t=862s
[14]: https://antonioleiva.com/kotlin-android-developers-book/



Do you want to contribute?
--------------------------

Feel free to report or add any useful feature, I will be glad to improve it with your help.

Developed By
------------

* Erik Jhordan Rey  - <erikjhordan.rey@gmail.com> or <erik.gonzalez@schibsted.com>

License
-------

    Copyright 2018 Erik Jhordan Rey

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

